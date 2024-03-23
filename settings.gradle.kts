// https://docs.gradle.org/current/userguide/declaring_dependencies.html
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeAnimations"

// Modules
include(":app")
include(":easing")
include(":value-animator")
