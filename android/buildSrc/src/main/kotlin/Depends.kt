object Android {
    object Version {
        const val compileSdk = 32
        const val minSdk = 21
        const val targetSdk = 32

        const val compose = "1.2.0-alpha06"
    }

    object Dependency {
        const val core ="androidx.core:core-ktx:1.7.0"

        // Test
        const val junit ="junit:junit:4.13.2"
        const val junit_ext ="androidx.test.ext:junit:1.1.3"
        const val espresso_core ="androidx.test.espresso:espresso-core:3.4.0"
    }
}

object Kotlin {
    object Version {
        const val kotlin = "1.6.10"
    }

    object Dependency {
        // JSON
        const val json ="org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    }
}