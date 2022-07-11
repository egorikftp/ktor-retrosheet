plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
}

repositories {
    mavenLocal()
}

group = "com.egoriku.ktor.retrosheet"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                api(projects.kotlinxSerializationCsv)
               // api("kotlinx.serialization.csv:kotlinx-serialization-csv:1.0.0")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("io.ktor:ktor-client-core:2.0.3")
                implementation("io.ktor:ktor-client-json:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
            }
        }
    }
}