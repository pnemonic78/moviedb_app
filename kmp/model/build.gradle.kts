plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "model"
//            isStatic = true
//        }
//    }
}

android {
    namespace = "com.tikalk.tmdb.model"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.logging.napier)
    implementation(libs.serialization.json)

    testImplementation(libs.kotlin.test)
}

// FIXME: Cannot locate tasks that match ':model:testClasses' as task 'testClasses' not found in project ':model'.
tasks.register("testClasses") {
}
