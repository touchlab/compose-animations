plugins {
    alias(libs.plugins.maven.publish) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.composeCompiler) apply false
}

val GROUP: String by project
val VERSION_NAME: String by project

allprojects {
    group = GROUP
    version = VERSION_NAME

    extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.apply {
        sourceSets.all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}
