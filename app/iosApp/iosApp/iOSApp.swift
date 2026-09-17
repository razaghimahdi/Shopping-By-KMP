import SwiftUI
import shared
import GoogleMaps


@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("YOUR_API_KEY")
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
