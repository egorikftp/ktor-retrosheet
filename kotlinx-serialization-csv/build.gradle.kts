plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "kotlinx.serialization.csv"
version = "1.0.0"

kotlin {
    jvm()

    sourceSets {
        val serialization = "1.3.3"
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}