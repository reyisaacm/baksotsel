import SwiftUI
import GoogleMaps //add import

@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey(Keys.googleAPIKey)
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
