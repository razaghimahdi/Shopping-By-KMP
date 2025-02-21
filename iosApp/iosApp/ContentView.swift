import UIKit
import SwiftUI
import shared
import GoogleMaps
 

class MapViewCoordinator: NSObject, GMSMapViewDelegate, CLLocationManagerDelegate {
    var parent: GoogleMapView
    var locationManager = CLLocationManager()
    var marker = GMSMarker()

    init(parent: GoogleMapView) {
        self.parent = parent
        super.init()
        
        locationManager.delegate = self
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
    }

    // When user taps on the map, update the marker position
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        parent.userLatitude = coordinate.latitude
        parent.userLongitude = coordinate.longitude
        parent.didSelectLocation(coordinate.latitude, coordinate.longitude)
        
        // Update marker position
        marker.position = coordinate
        marker.map = mapView
    }

    // When user’s location updates, update the marker
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.first else { return }
        
        parent.userLatitude = location.coordinate.latitude
        parent.userLongitude = location.coordinate.longitude
        
        // Move marker to current location
        marker.position = location.coordinate
    }

    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("Error getting user location: \(error.localizedDescription)")
    }
}


struct GoogleMapView: UIViewRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLongitude: Double
    var didSelectLocation: (Double, Double) -> Void
    
    func makeUIView(context: Context) -> GMSMapView {
        let options = GMSMapViewOptions()
        options.camera = GMSCameraPosition.camera(withLatitude: userLatitude, longitude: userLongitude, zoom: 10.0)

        let mapView = GMSMapView(options: options)
        mapView.delegate = context.coordinator
        
        // Add a marker
        context.coordinator.marker.map = mapView
        context.coordinator.marker.position = CLLocationCoordinate2D(latitude: userLatitude, longitude: userLongitude)
        
        return mapView
    }

    func updateUIView(_ uiView: GMSMapView, context: Context) {
        // Update marker position when userLatitude/userLongitude change
        context.coordinator.marker.position = CLLocationCoordinate2D(latitude: userLatitude, longitude: userLongitude)
    }

    func makeCoordinator() -> MapViewCoordinator {
        return MapViewCoordinator(parent: self)
    }
}


struct ComposeView: UIViewControllerRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLongitude: Double

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.mainViewController(
            mapUIViewController: { () -> UIViewController in
                return UIHostingController(rootView: GoogleMapView(userLatitude: $userLatitude, userLongitude: $userLongitude, didSelectLocation: { lat, lon in
                    self.userLatitude = lat
                    self.userLongitude = lon
                }))
            },
            latitude: userLatitude,
            longitude: userLongitude
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


struct ContentView: View {
    @State private var userLatitude: Double = 12.952636
    @State private var userLongitude: Double = 77.653059

    var body: some View {
        ComposeView(userLatitude: $userLatitude, userLongitude: $userLongitude)
            .ignoresSafeArea(.keyboard) // Compose has its own keyboard handler
    }
}
