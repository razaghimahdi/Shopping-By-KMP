package common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

actual suspend fun Context.putData(key: String, `object`: String) {
}

actual suspend inline fun Context.getData(key: String): String? {
    return ""
}
