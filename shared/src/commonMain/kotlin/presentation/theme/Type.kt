package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import common.font

// Set of Material typography styles to start with
val Typography = Typography(
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun LatoTypography(): Typography {
    val lato = FontFamily(
        font(
            res = "lato_regular",
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        font(
            res = "lato_thin",
            weight = FontWeight.Thin,
            style = FontStyle.Normal
        ),
        font(
            res = "lato_italic",
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        font(
            res = "lato_light_italic",
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        font(
            res = "lato_thin_italic",
            weight = FontWeight.Thin,
            style = FontStyle.Italic
        ),
        font(
            res = "lato_bold_italic",
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
        font(
            res = "lato_black_italic",
            weight = FontWeight.Black,
            style = FontStyle.Italic
        ),
        font(
            res = "lato_bold",
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        font(
            res = "lato_black",
            weight = FontWeight.Black,
            style = FontStyle.Normal
        ),
        font(
            res = "lato_light",
            weight = FontWeight.Light,
            style = FontStyle.Normal
        ),
        font(
            res = "lato_thin",
            weight = FontWeight.Thin,
            style = FontStyle.Normal
        ),
    )

    return Typography(
        headlineSmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            fontFamily = lato
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            fontFamily = lato
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = lato
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = lato
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            fontFamily = lato
        ),
        /*labelLarge = TextStyle(fontFamily = Lato),
        labelSmall = TextStyle(fontFamily = Lato),
        bodySmall = TextStyle(fontFamily = Lato),
        titleMedium = TextStyle(fontFamily = Lato),
        titleSmall = TextStyle(fontFamily = Lato),
        headlineLarge = TextStyle(fontFamily = Lato),
        headlineMedium = TextStyle(fontFamily = Lato),
        displayLarge = TextStyle(fontFamily = Lato),
        displayMedium = TextStyle(fontFamily = Lato),
        displaySmall = TextStyle(fontFamily = Lato),*/
    )
}
