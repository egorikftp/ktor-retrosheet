plugins {
    kotlin("multiplatform") //version "1.7.0"
    kotlin("plugin.serialization")// version "1.7.0"
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

                implementation("io.ktor:ktor-client-cio:2.0.3")
                implementation("io.ktor:ktor-client-core:2.0.3")
                implementation("io.ktor:ktor-client-json:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.3.0")
            }
        }
    }
}