// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    // SDK and tools
    val kotlinVersion by extra("1.4.32")
    val minSdkVersion by extra(21)
    val targetSdkVersion by extra(30)
    val compileSdkVersion by extra(31)

    // App dependencies
    val composeVersion by extra("1.0.0-beta09")
    val dagger2Version by extra("2.27")
    val espressoVersion by extra("3.3.0")
    val glideVersion by extra("4.11.0")
    val junitVersion by extra("4.13")
    val okhttp3Version by extra("4.8.0")
    val retrofit2Version by extra("2.9.0")
    val rxandroid2Version by extra("2.1.1")
    val rxjavaVersion by extra("2.1.9")
    val rxkbinding2Version by extra("2.2.0")
    val rxkotlin2Version by extra("2.3.0")
    val sqlbrite2Version by extra("2.0.0")
    val timberVersion by extra("4.7.1")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
