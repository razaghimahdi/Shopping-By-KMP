package business.core

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import common.Context
import common.getData
import common.putData
import kotlinx.coroutines.flow.first

const val APP_DATASTORE = "com.razzaghi.shoppingbykmp"

class AppDataStoreManager(val context: Context) : AppDataStore {

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.putData(key, value)
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.getData(key)
    }
}