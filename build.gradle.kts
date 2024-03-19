// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id ("com.android.library") version "7.4.1" apply false
    id ("com.google.dagger.hilt.android") version "2.50" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31" apply false
}