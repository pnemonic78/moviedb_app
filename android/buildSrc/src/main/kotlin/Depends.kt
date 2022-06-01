object Android {
    object Version {
        const val compileSdk = 32
        const val minSdk = 21
        const val targetSdk = 32

        const val compose = "1.2.0-beta02"
        const val hilt = "2.42"
        const val navigation = "2.5.0-rc01"
        const val okhttp = "4.9.3"
        const val test = "1.4.0"
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
        const val navigationCompose = "androidx.navigation:navigation-compose:${Version.navigation}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    }

    object Image {
        const val coil = "io.coil-kt:coil-compose:1.3.2"
        const val ratingbar = "io.github.a914-gowtham:compose-ratingbar:1.2.2"
    }

    object JSON {
        const val kotlin = Kotlin.JSON.serialization
        const val retrofit = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Logging {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Network {
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val junit_ext = "androidx.test.ext:junit:1.1.3"
        const val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
        const val runner = "androidx.test:runner:${Version.test}"
        const val rules = "androidx.test:rules:${Version.test}"
    }
}

object Kotlin {
    object Version {
        const val kotlin = "1.6.21"
    }

    object JSON {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
    }
}