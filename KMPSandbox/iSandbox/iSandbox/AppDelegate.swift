import UIKit
import SandboxSDK

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    let wrapper = GenericWrapper(value: "Wrapped String" as NSString)
    let coroutinesTestExecutor = CoroutinesTest()
    let api = Repository()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
       
        let value = wrapper.pull()
        coroutinesTestExecutor.executeInBackground(success: { _ in
            print("Success: in thread - \(Thread.current)")
        }) { _ in
            print("Failure: in thread - \(Thread.current)")
        }

//        coroutinesTestExecutor.execute { (result, error) in
//            print("Result: \(result) and error: \(error) - in thread: \(Thread.current)")
//        }
//
//        coroutinesTestExecutor.exec(string: "String from iOS") { (result, error) in
//            print("Result: \(result) and error: \(error) - in thread: \(Thread.current)")
//        }
        
        api.fetchMembers(success: { (members) in
            print(members)
        }) { (error) in
            print(error)
        }
        
        api.executeInBackground(success: { (firstMember) in
            print(firstMember)
        }) { (error) in
            print(error)
        }
        
        
        return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


}

