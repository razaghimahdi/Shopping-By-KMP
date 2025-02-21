import androidx.compose.ui.window.ComposeUIViewController
import common.Context
import platform.UIKit.UIViewController
import presentation.App


fun mainViewController(
    mapUIViewController: () -> UIViewController,
    latitude: Double,
    longitude: Double,
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    globalLatitude = latitude
    globalLongitude = longitude
    App(Context())
}

lateinit var mapViewController: () -> UIViewController
var globalLatitude: Double = 0.0
var globalLongitude: Double = 0.0


// fun mainViewController() = ComposeUIViewController { App(Context()) }

