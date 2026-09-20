package com.razzaghi.shopingbykmp.presentation.util

import kotlin.math.abs

actual fun Format(value: Int): String {
    val isNegative = value < 0

    // Convert to Long to prevent overflow if the value is Int.MIN_VALUE
    val absoluteString = abs(value.toLong()).toString()

    // Reverses the string, groups into chunks of 3, adds commas, and reverses back
    val formatted = absoluteString
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return if (isNegative) "-$formatted" else formatted
}