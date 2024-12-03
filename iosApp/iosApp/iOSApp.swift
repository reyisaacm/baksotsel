import SwiftUI
import GoogleMaps //add import

@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}