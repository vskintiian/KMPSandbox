import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
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

//    maven {
//        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
//    }
//    maven {
//        url = uri("https://dl.bintray.com/kotlin/kotlin-dev")
//    }

    maven {
        url = uri("https://dl.bintray.com/kotlin/ktor/")
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

    targets.getByName<KotlinNativeTarget>("ios").compilations["main"].kotlinOptions.freeCompilerArgs +=
        listOf("-Xobjc-generics", "-Xg0")

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common", "1.3.72"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5-native-mt")

                implementation("io.ktor:ktor-client-core:1.3.2")
                implementation("io.ktor:ktor-client-json:1.3.2")
                implementation("io.ktor:ktor-client-logging:1.3.2")

                implementation("io.ktor:ktor-client-serialization:1.3.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5-native-mt")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk7", "1.3.72"))
                implementation("androidx.core:core-ktx:1.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5-native-mt")

                implementation("io.ktor:ktor-client-android:1.3.2")
                implementation("io.ktor:ktor-client-core-jvm:1.3.2")
                implementation("io.ktor:ktor-client-json-jvm:1.3.2")
                implementation("io.ktor:ktor-client-logging-jvm:1.3.2")

                implementation("io.ktor:ktor-client-serialization-jvm:1.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
            }
        }
        val androidTest by getting

        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
//
                implementation("io.ktor:ktor-client-ios:1.3.2")
                implementation("io.ktor:ktor-client-core-native:1.3.2")
                implementation("io.ktor:ktor-client-json-native:1.3.2")
                implementation("io.ktor:ktor-client-logging-native:1.3.2")

                implementation( "io.ktor:ktor-client-serialization-native:1.3.2")
                implementation( "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
            }
        }
        val iosTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5-native-mt")
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

//    packagingOptions {
//        pickFirst("META-INF/ktor-client-logging.kotlin_module")
//        pickFirst("META-INF/kotlinx-io.kotlin_module")
//        pickFirst("META-INF/atomicfu.kotlin_module")
//        pickFirst("META-INF/kotlinx-coroutines-io.kotlin_module")
//    }

//    compileOptions {
//        sourceCompatibility(JavaVersion.VERSION_1_8)
//        targetCompatibility(JavaVersion.VERSION_1_8)
//    }
}