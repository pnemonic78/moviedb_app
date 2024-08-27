plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
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
//            baseName = "shared"
//            isStatic = true
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kamel)
            implementation(libs.bundles.compose)
            implementation(libs.bundles.precompose)
            implementation(libs.compose.ratingbar)
            implementation(libs.kotlin.coroutines)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.logging.napier)
            implementation(libs.navigation.compose)
            implementation(libs.paging.compose)
            implementation(compose.components.resources)

            implementation(projects.featureCast)
            implementation(projects.featureNowPlaying)
            implementation(projects.featurePopular)
            implementation(projects.featureTopRated)
            implementation(projects.featureUpcoming)
            implementation(projects.uiCommon)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
//        iosMain.dependencies {
//            implementation(libs.ktor.client.darwin)
//        }
    }
}

android {
    namespace = "com.tikalk.tmdb.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
}

compose {
    resources {
        generateResClass = always
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.model)
}

// FIXME: Cannot locate tasks that match ':shared:testClasses' as task 'testClasses' not found in project ':shared'.
tasks.register("testClasses") {
}