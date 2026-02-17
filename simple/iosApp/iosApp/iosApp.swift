import SwiftUI
import toastKMPKit
@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            //  ContentView()
            Button("Show Toast") {
                NativeShowToast.shared.show(msg: "Native toast from swft", type: NativeToastType.long_

                )
            }
        }
    }
}
