buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4-M3")
        classpath("com.android.tools.build:gradle:4.1.0-beta03")
    }
}
group = "me.vskintiian"
version = "0.1"

repositories {
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
