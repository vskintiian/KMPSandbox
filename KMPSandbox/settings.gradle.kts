pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
        jcenter()
//        maven {
//            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
//        }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:3.5.2")
            }
        }
    }
}
rootProject.name = "KMPSandbox"

include(":SandboxSDK")
include(":androidApp")

