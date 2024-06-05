buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle)
        // Dependency Injection
        classpath(libs.di.hilt.gradle)
    }
}
