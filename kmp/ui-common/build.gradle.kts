plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ui-common"
            isStatic = true
        }
    }
}

android {
    namespace = "com.tikalk.tmdb.ui.common"
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
    api(projects.domain)
    api(libs.bundles.compose)
    implementation(libs.bundles.precompose)
    implementation(libs.compose.ratingbar)
    implementation(libs.kamel)
    implementation(libs.kotlin.coroutines)
    implementation(libs.logging.napier)
    api(libs.navigation.compose)
    implementation(libs.paging.compose)
    implementation(compose.components.resources)
    implementation(kotlin("reflect"))
}
