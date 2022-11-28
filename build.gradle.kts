import magik.github
import org.lwjgl.lwjgl
import org.lwjgl.lwjgl.Module.*

plugins {
    id("com.android.library")
    id("kotlin-android")
//    kotlin("jvm") version embeddedKotlinVersion
    id("org.lwjgl.plugin") version "0.0.29"
    id("elect86.magik") version "0.3.1"
    `maven-publish`
}



android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    sourceSets.getByName("test") {
//        java.srcDir("src/test/java")
//        java.srcDir("src/test/kotlin")
//    }

}

repositories {
    google()
    mavenCentral()
    github("kotlin-graphics/mary")
}

dependencies {
    implementation(kotlin("stdlib-jdk8", embeddedKotlinVersion))
    implementation("kotlin.graphics:unsigned:3.3.31")
    implementation("kotlin.graphics:kool:0.9.68")
    lwjgl { implementation(glfw, jemalloc, openal, opengl, stb) }

    testImplementation("io.kotest:kotest-runner-junit5:5.4.1")
    testImplementation("io.kotest:kotest-assertions-core:5.4.1")
}

//kotlin.jvmToolchain {
//    this as JavaToolchainSpec
//    languageVersion.set(JavaLanguageVersion.of(8))
//}

//tasks {
//    withType<KotlinCompile<*>>().all {
//        kotlinOptions {
//            freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
//        }
//    }
//}

//publishing {
//    publications {
//        createGithubPublication {
//            from(components["java"])
//            suppressAllPomMetadataWarnings()
//        }
//    }
//    repositories {
//        github {
//            domain = "kotlin-graphics/mary"
//        }
//    }
//}

//java { withSourcesJar() }