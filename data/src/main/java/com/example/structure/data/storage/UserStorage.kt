package com.example.structure.data.storage

import android.content.SharedPreferences
import com.example.structure.data.storage.SharedPreferenceConst.PREF_SECURE_USER

interface UserStorage {
    fun save(user: String)

    fun get(): String?

    fun clear()
}

class UserStorageImpl(pref: SharedPreferences) : UserStorage {
    private var user: String? by pref.reference(PREF_SECURE_USER)

    override fun save(user: String) {
        this.user = user
    }

    override fun get(): String? {
        return user
    }

    override fun clear() {
        user = null
    }

}
