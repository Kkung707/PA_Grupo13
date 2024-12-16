// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    repositories {
        google() // Repositório do Google
        mavenCentral() // Maven Central para dependências
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0") // Plugin do Google Services
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
