plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "co.touchlab.compose.animations"
        minSdk = 21
        targetSdk = 30
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Libs
    implementation(projects.easing)
    implementation(projects.valueAnimatorCompat)
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
