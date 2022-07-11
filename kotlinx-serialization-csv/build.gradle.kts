plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("me.tylerbwong.gradle.metalava") version "0.2.3"
}

group = "kotlinx.serialization.csv"
version = "1.0.0"

repositories {
    google()
}

metalava {
    filename = "api/$name-api.txt"
    reportLintsAsErrors = true
}

kotlin {
    jvm()

    sourceSets {
        val serialization = "1.3.3"
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization")
            }
        }
    }
}