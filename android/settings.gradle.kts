rootProject.name = "Movie DB - Android"
include(":app")
include(":model")
include(":domain")
include(":ui-common")
include(":feature-now-playing")

dependencyResolutionManagement {
    versionCatalogs {
        create("androidCatalog") {
            version("compose", "1.3.3")
            version("composeCompiler", "1.3.1")
            version("composeMaterial", "1.3.1")
            version("gradle", "7.4.1")
            version("hilt", "2.44.2")
            version("navigation", "2.5.0")
            version("okhttp", "4.9.3")
            version("room", "2.4.3")
            version("test", "1.4.0")

            // Database
            library("db-roomCompiler", "androidx.room", "room-compiler").versionRef("room")
            library("db-roomKtx", "androidx.room", "room-ktx").versionRef("room")

            // Dependency Injection
            library("di-hilt", "com.google.dagger", "hilt-android").versionRef("hilt")
            library("di-hiltCompiler", "com.google.dagger", "hilt-android-compiler")
                .versionRef("hilt")

            // UI
            library("image-coil", "io.coil-kt:coil-compose:1.3.2")
            library("image-ratingbar", "io.github.a914-gowtham:compose-ratingbar:1.2.2")

            // Jetpack
            library("jetpack-appcompat", "androidx.appcompat:appcompat:1.4.1")
            library("jetpack-material", "androidx.compose.material", "material")
                .versionRef("composeMaterial")
            library("jetpack-composeTooling", "androidx.compose.ui", "ui-tooling")
                .versionRef("compose")
            library("jetpack-compose", "androidx.compose.ui", "ui")
                .versionRef("compose")
            library("jetpack-core", "androidx.core:core-ktx:1.7.0")
            library("jetpack-navigationCompose", "androidx.navigation", "navigation-compose")
                .versionRef("navigation")
            library("jetpack-navigationFragment", "androidx.navigation", "navigation-fragment-ktx")
                .versionRef("navigation")

            // JSON
            library(
                "json-retrofit",
                "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
            )

            // Logging
            library("log-timber", "com.jakewharton.timber:timber:5.0.1")

            // Network
            library("net-logging", "com.squareup.okhttp3", "logging-interceptor")
                .versionRef("okhttp")
            library("net-okhttp", "com.squareup.okhttp3", "okhttp")
                .versionRef("okhttp")
            library("net-retrofit", "com.squareup.retrofit2:retrofit:2.9.0")

            // Test
            library("test-junit", "junit:junit:4.13.2")
            library("test-junitExt", "androidx.test.ext:junit:1.1.3")
            library("test-espresso", "androidx.test.espresso:espresso-core:3.4.0")
            library("test-runner", "androidx.test", "runner").versionRef("test")
            library("test-rules", "androidx.test", "rules").versionRef("test")

            plugin("app", "com.android.application").versionRef("gradle")
            plugin("lib", "com.android.library").versionRef("gradle")
            plugin("hilt", "dagger.hilt.android.plugin").versionRef("hilt")
        }
        create("kotlinCatalog") {
            version("kotlin", "1.7.10")

            library(
                "json-serialization",
                "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0"
            )

            plugin("android", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("kapt", "org.jetbrains.kotlin.kapt").versionRef("kotlin")
            plugin("serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
        }
    }
}
