import SwiftUI

@main
struct iOSApp: App {
    @StateObject var appObj = AppObservableObject()
    @Environment(\.scenePhase) var scenePhase
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(appObj)
                .onChange(of: scenePhase) { newPhase in
                    if newPhase == .active {
                        appObj.nav.onReEnterForeground()
                    } else if newPhase == . background {
                        appObj.nav.onEnterBackground()
                    }
                }
                .onReceive(NotificationCenter.default.publisher(for: UIDevice.orientationDidChangeNotification)) { _ in
                    appObj.nav.onChangeOrientation()
                }
        }
    }
}
