import SwiftUI
import SandboxSDK

struct ContentView: View {
    let testStringProvider = KotlinTestClass()
    
    var body: some View {
        Text(testStringProvider.expectedString())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
