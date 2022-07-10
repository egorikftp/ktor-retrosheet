plugins {
    kotlin("multiplatform") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.1"
}

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
}