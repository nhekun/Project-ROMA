plugins {
    id("org.jetbrains.kotlin.jvm")
}

group = "com.roma.roman"
version = "1.0.0"

dependencies {
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.24")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.9.24")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
        jvmTarget = "21"
    }
}