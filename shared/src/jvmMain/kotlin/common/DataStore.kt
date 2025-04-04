package common

import java.io.File
import java.util.Properties


private val file = File("preferences.properties")
private val properties = Properties()

actual suspend fun Context?.getData(key: String): String? {
    return properties.getProperty(key)
}

actual suspend fun Context?.putData(key: String, `object`: String) {
    properties[key] = `object`
    file.outputStream().use { properties.store(it, null) }
}

