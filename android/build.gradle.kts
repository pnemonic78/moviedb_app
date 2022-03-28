// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    // SDK and tools
    val kotlinVersion by extra("1.6.10")
    val minSdkVersion by extra(21)
    val targetSdkVersion by extra(32)
    val compileSdkVersion by extra(32)

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // DI
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
