plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.roma.roman"
version = "1.0.0"

dependencies {
    testImplementation(libs.bundles.test.junit5)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}