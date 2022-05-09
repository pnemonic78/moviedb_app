object Android {
    object Version {
        const val compileSdk = 32
        const val minSdk = 21
        const val targetSdk = 32

        const val compose = "1.2.0-alpha08"
        const val hilt = "2.38.1"
    }

    // Dependency Injection
    object Inject {
        const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    }

    object Jetpack {
        const val appcompat = "androidx.appcompat:appcompat:1.4.1"
        const val material = "androidx.compose.material:material:${Version.compose}"
        const val livedata = "androidx.compose.runtime:runtime-livedata:${Version.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        const val compose = "androidx.compose.ui:ui:${Version.compose}"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val core = "androidx.core:core-ktx:1.7.0"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:2.4.2"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:2.4.2"
    }

    object Image {
        const val glide = "com.github.bumptech.glide:glide:4.12.0"
        const val coil = "io.coil-kt:coil-compose:1.3.2"
        const val ratingbar = "io.github.a914-gowtham:compose-ratingbar:1.2.2"
    }

    object JSON {
        const val kotlin = Kotlin.Dependency.json
        const val retrofit = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Network {
        const val logging = "com.squareup.okhttp3:logging-interceptor:4.9.3"
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.3"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val junit_ext = "androidx.test.ext:junit:1.1.3"
        const val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
        const val runner = "androidx.test:runner:1.4.0"
        const val rules = "androidx.test:rules:1.4.0"
    }
}

object Kotlin {
    object Version {
        const val kotlin = "1.6.10"
    }

    object Dependency {
        // JSON
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    }
}