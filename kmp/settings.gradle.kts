enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Movie_DB_KMP"
include(":androidApp")
include(":domain")
include(":feature-cast")
include(":feature-now-playing")
include(":feature-popular")
include(":feature-top-rated")
include(":feature-upcoming")
include(":model")
include(":shared")
include(":ui-common")
