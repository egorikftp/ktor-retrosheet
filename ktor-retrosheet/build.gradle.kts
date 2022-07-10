plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.kotlinxSerializationCsv)

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("io.ktor:ktor-client-cio:2.0.3")
                implementation("io.ktor:ktor-client-core:2.0.3")
                implementation("io.ktor:ktor-client-json:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
            }
        }

    }
}