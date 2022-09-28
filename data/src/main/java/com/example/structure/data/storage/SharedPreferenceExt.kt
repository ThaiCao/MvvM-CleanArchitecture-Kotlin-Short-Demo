package com.example.structure.data.storage

import android.content.SharedPreferences
import com.example.structure.common.extend.safe
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

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        val editor = prefers.edit()
        set(editor, value)
        editor.commit()
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

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        val editor = prefers.edit()
        val valueSerialized = serialize.serialize(value)
        editor.putString(key, valueSerialized)
        editor.commit()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        val result = prefers.getString(key, "") ?: ""
        if (result.isBlank()) return null

        return try {
            serialize.deserialize(
                value = result,
                type = type,
            )
        } catch (ex: Exception) {
            setValue(thisRef, property, null)
            null
        }
    }
}

object SharedPreferenceConst {
    const val PREF_APP = "pref_app"
    const val PREF_SECURE_USER = "pref_secure_user"
    const val PREF_SECURE_USER_TOKEN = "pref_secure_user_token"
    const val PREF_LANGUAGE = "pref_language"
    const val PREF_AWS_COGNITO = "pref_aws_cognito"
}
