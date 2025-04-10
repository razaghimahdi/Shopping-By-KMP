package common

import kotlinx.browser.window


actual suspend fun Context?.getData(key: String): String? {
    return window.localStorage.getItem(key)
}

actual suspend fun Context?.putData(key: String, `object`: String) {
    window.localStorage.setItem(key, `object`)
}

