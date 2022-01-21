# KMPSandbox
A sandbox project to evaluate the Kotlin multiplatform capabilities

#### Do not change android project name during creation of multiplatform project. then it wont ne bossible to import it to the Android Studio

#### To create gradlew file run in the root directory of the project
`$ gradle wrapper --gradle-version 6.5.1`


#### Configuring the project with cocoapods
1. [Install](https://guides.cocoapods.org/using/getting-started.html#installation) CocoaPods.
   It's recommended to use CocoaPods 1.6.1 or higher.

2. Navigate to the **SandboxSDK** directory and run
    
    `$ ./gradlew podspec`

      A [podspec](https://guides.cocoapods.org/syntax/podspec.html#specification) file for the Kotlin/Native library will be generated.
      
3. Navigate to the **iosApp** directory and install the dependencies. The generated
   podspec is already added to the Podfile, so just run
    
    `$ pod install`

4. Open [ios-app.xcworkspace](ios-app/ios-app.xcworkspace) in Xcode and run the build.

5. The plugin generates a static framework as default. To use dynamic one just delete the line in podspec file:
   
   `spec.static_framework         = true`

   or replace it with:

   `spec.static_framework         = false`


   Errors:
   - Invoke-customs are only supported starting with Android O (--min-api 26)
   -- set 'minSdkVersion = 26' in 'android.defaultConfig.minSdkVersion' (https://github.com/google/ExoPlayer/issues/6913)
