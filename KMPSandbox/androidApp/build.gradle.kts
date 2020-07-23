plugins {
    id("com.android.application")
    kotlin("android")
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
}

dependencies {
    implementation(project(":SandboxSDK"))
    implementation("androidx.core:core-ktx:1.2.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation(kotlin("stdlib-jdk7"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5-native-mt")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "me.vskintiian.androidApp"
        minSdkVersion(26)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}