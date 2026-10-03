pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.application") {
                useVersion("8.6.0")
            }
            if (requested.id.id == "com.android.library") {
                useVersion("8.6.0")
            }
            if (requested.id.id == "org.jetbrains.kotlin.android") {
                useVersion("2.0.21")
            }
            if (requested.id.id == "org.jetbrains.kotlin.jvm") {
                useVersion("2.0.21")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "ROMA"

include(":app")
include(":launcher")
include(":roman")
include(":ui")