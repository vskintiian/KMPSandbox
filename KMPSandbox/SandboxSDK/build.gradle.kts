import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-android-extensions")
}
group = "me.vskintiian"
version = "0.1"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}
kotlin {
    android()

    val iosTarget = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (iosTarget) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common", "1.4-M2-eap-23"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7-native-mt-1.4-M2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7-native-mt-1.4-M2")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk7", "1.4-M2-eap-23"))
                implementation("androidx.core:core-ktx:1.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7-native-mt-1.4-M2")
            }
        }
        val androidTest by getting

        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7-native-mt-1.4-M2")
            }
        }
        val iosTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7-native-mt-1.4-M2")
            }
        }
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Shared SandboxSDK module for both iOS and Android platforms"
        homepage = "sandboxSDK.com"

        // The name of the produced framework can be changed.
        // The name of the Gradle project is used here by default.
        frameworkName = "SandboxSDK"
    }
}
android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}