plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.roma.launcher"
    compileSdk = 34

    defaultConfig {
        minSdk = 29
        targetSdk = 34
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":roman"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.androidx.lifecycle)
}