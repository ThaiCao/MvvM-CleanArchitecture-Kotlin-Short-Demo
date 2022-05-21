package com.example.mydemo.local.sharedPreferences

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mydemo.common.utils.safe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun SharedPreferences.boolean(key: String, def: Boolean = false) = PrimitivePreference(
    this,
    set = { putBoolean(key, it) },
    get = { getBoolean(key, def) }
)

fun SharedPreferences.string(key: String, def: String = "") = PrimitivePreference(
    this,
    set = { putString(key, it) },
    get = { getString(key, def) ?: def }
)

fun SharedPreferences.long(key: String, def: Long = 0) = PrimitivePreference(
    this,
    set = { putLong(key, it) },
    get = { getLong(key, def) }
)

fun SharedPreferences.float(key: String, def: Float = 0F) = PrimitivePreference(
    this,
    set = { putFloat(key, it) },
    get = { getFloat(key, def) }
)

fun SharedPreferences.double(key: String, def: Double = 0.0) = PrimitivePreference(
    this,
    set = { putString(key, it.toString()) },
    get = { getString(key, def.toString())?.toDoubleOrNull().safe(def) }
)

inline fun <reified T> SharedPreferences.reference(key: String): ReferencePreference<T> {
    val type = object : TypeToken<T>() {}.type
    return ReferencePreference(key, type, this, ObjectSerialize.Json)
}
open class PrimitivePreference<T>(
    private val prefers: SharedPreferences,
    private val set: SharedPreferences.Editor.(T) -> Unit,
    private val get: SharedPreferences.() -> T
) : ReadWriteProperty<Any, T> {

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val editor = prefers.edit()
        set(editor, value)
        editor.apply()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return get(prefers)
    }
}

interface ObjectSerialize {
    fun <T> serialize(value: T?): String
    fun <T> deserialize(value: String, type: Type): T?

    object Json : ObjectSerialize {
        override fun <T> serialize(value: T?): String {
            return Gson().toJson(value)
        }

        override fun <T> deserialize(value: String, type: Type): T? {
            return Gson().fromJson(value, type)
        }
    }
}

class ReferencePreference<T>(
    private val key: String,
    private val type: Type,
    private val prefers: SharedPreferences,
    private val serialize: ObjectSerialize
) : ReadWriteProperty<Any, T?> {

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        val editor = prefers.edit()
        val valueSerialized = serialize.serialize(value)
        editor.putString(key, valueSerialized)
        editor.apply()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        val result = prefers.getString(key, "") ?: ""
        if (result.isBlank()) return null
        return serialize.deserialize(result, type)
    }
}

object SharedPreferenceConst {
    const val PREF_APP = "pref_app"
    const val PREF_EXPIRED_TIME = "pref_expired_time"
}
