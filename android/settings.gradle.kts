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

rootProject.name = "Movie_DB_Android"
include(":app")
include(":model")
include(":domain")
include(":ui-common")
include(":feature-cast")
include(":feature-now-playing")
include(":feature-popular")
include(":feature-top-rated")
include(":feature-upcoming")
