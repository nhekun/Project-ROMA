// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

allprojects {
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            jvmTarget = libs.versions.jvmTarget.get()
        }
    }
}

subprojects {
    if (project.plugins.hasPlugin("com.android.library") || project.plugins.hasPlugin("com.android.application")) {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
            kotlinOptions {
                freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            }
        }
    }
}