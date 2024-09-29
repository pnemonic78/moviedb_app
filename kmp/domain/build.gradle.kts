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
//            baseName = "domain"
//            isStatic = true
//        }
//    }

//    sourceSets {
//        commonMain.dependencies {
//            implementation(libs.kotlin.coroutines)
//            implementation(libs.logging.napier)
//            implementation(libs.serialization.json)
//        }
//        commonTest.dependencies {
//            implementation(libs.kotlin.test)
//        }
//        androidMain.dependencies {
//            implementation(libs.ktor.client.android)
//        }
//        iosMain.dependencies {
//            implementation(libs.ktor.client.darwin)
//        }
//    }
}

android {
    namespace = "com.tikalk.tmdb.domain"
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
    api(projects.model)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
}

// FIXME: Cannot locate tasks that match ':domain:testClasses' as task 'testClasses' not found in project ':domain'.
tasks.register("testClasses") {
}
