rootProject.name = "glm"


pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
//    includeBuild("../build-logic")
}

gradle.rootProject {
    group = "kotlin.graphics"
    version = "0.9.9.1-5"
}

buildscript {
    repositories {
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath(kotlin("gradle-plugin", version = "1.5.10"))
    }
}