package com.example.structure.network

import android.content.SharedPreferences
import com.example.structure.data.storage.reference
import java.util.*

interface HeaderProvider {
    fun getUUID(): String

    companion object {
        const val PREF_HEADER_PROVIDER = "pref_header_provider"
    }
}

class HeaderProviderImpl(pref: SharedPreferences) : HeaderProvider {

    private var uuid: String? by pref.reference(PREF_UUID)

    companion object {
        const val PREF_UUID = "pref_uuid"
    }

    private fun saveUUID( _uuid: String) {
        uuid = _uuid
    }

    private fun createUUIDAndSave(): String {
        val uuid = UUID.randomUUID().toString()
        saveUUID(uuid)
        return uuid
    }

    override fun getUUID(): String {
        var uuid = uuid
        if (uuid.isNullOrBlank()) {
            uuid = createUUIDAndSave()
        }
        return uuid
    }

}
