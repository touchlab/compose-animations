plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
}

android {
    namespace = "co.touchlab.compose.animations"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Libs
    implementation(projects.easing)
    implementation(projects.valueAnimator)

    // External Libs
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.appCompat)
    androidTestImplementation(libs.androidx.androidJunit)
    androidTestImplementation(libs.androidx.espresso)

    implementation(libs.google.material)
    testImplementation(libs.junit)

    implementation(libs.compose.ui)
    implementation(libs.compose.animation)
    implementation(libs.compose.material)
    implementation(libs.compose.toolingPreview)
    androidTestImplementation(libs.compose.test)
    debugImplementation(libs.compose.tooling)

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha05")
}
