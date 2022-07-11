plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("me.tylerbwong.gradle.metalava") version "0.2.3"
}

repositories {
    google()
}

metalava {
    filename = "api/$name-api.txt"
    reportLintsAsErrors = true
}

group = "com.egoriku.ktor.retrosheet"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api(projects.kotlinxSerializationCsv)

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("io.ktor:ktor-client-core:2.0.3")
                implementation("io.ktor:ktor-client-json:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
            }
        }
    }
}