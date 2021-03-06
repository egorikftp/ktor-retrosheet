plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

repositories {
    mavenLocal()
}

val implementation by configurations

dependencies {
    implementation(projects.ktorRetrosheet)
    // implementation("com.egoriku.ktor.retrosheet:ktor-retrosheet:1.0.0")

    implementation("io.ktor:ktor-client-core:2.0.3")
    implementation("io.ktor:ktor-client-cio:2.0.3")
    implementation("io.ktor:ktor-client-json:2.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.0.3")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")

}