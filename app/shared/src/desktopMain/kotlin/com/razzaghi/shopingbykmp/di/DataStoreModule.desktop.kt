package com.razzaghi.shopingbykmp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.AppDataStoreManager
import okio.Path.Companion.toPath
import org.koin.dsl.module
import java.io.File

actual fun dataStoreModule() = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                val appDir = File(System.getProperty("user.home"), ".shopping_by_kmp")
                if (!appDir.exists()) appDir.mkdirs()
                File(appDir, "shopping_prefs.preferences_pb").absolutePath.toPath()
            }
        )
    }
    single<AppDataStore> { AppDataStoreManager(get()) }
}