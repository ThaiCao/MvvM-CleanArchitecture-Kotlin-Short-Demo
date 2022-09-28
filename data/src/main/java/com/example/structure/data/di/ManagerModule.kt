package com.example.structure.data.di

import com.example.structure.data.storage.SharedPreferenceConst
import com.example.structure.data.storage.UserStorage
import com.example.structure.data.storage.UserStorageImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val managerModule = module {

    factory<UserStorage> {
        UserStorageImpl(
            pref = get(named(SharedPreferenceConst.PREF_SECURE_USER))
        )
    }

}
