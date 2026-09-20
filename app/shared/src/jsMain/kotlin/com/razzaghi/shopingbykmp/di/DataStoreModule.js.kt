package com.razzaghi.shopingbykmp.di

import com.razzaghi.shopingbykmp.business.core.AppDataStore
import kotlinx.browser.localStorage
import org.koin.dsl.module

class WebAppDataStoreManager : AppDataStore {
    override suspend fun setValue(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    override suspend fun readValue(key: String): String? {
        return localStorage.getItem(key)
    }
}

actual fun dataStoreModule() = module {
    single<AppDataStore> { WebAppDataStoreManager() }
}