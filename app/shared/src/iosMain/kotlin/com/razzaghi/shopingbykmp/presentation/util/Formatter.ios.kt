package com.razzaghi.shopingbykmp.presentation.util

import platform.Foundation.NSLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.localeWithLocaleIdentifier

actual fun Format(value: Int): String {
    val formatter = NSNumberFormatter().apply {
        numberStyle = NSNumberFormatterDecimalStyle
        // Forces the #,### comma separator style
        locale = NSLocale.localeWithLocaleIdentifier("en_US")
    }
    return formatter.stringFromNumber(NSNumber(value)) ?: value.toString()
}