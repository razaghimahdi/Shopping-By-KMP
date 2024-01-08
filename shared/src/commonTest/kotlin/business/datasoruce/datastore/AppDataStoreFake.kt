package business.datasoruce.datastore

import business.core.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow

class AppDataStoreFake : AppDataStore {

    private val data = MutableStateFlow<HashMap<String, String>>(hashMapOf())

    override suspend fun setValue(key: String, value: String) {
        data.value[key] = value
    }

    override suspend fun readValue(key: String): String? {
       return data.value[key]
    }
}