package common

import java.text.DecimalFormat

actual fun Format (value: Int): String{
    val formatter = DecimalFormat("#,###")
    return formatter.format(value)
}

