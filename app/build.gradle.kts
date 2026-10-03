plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.roma.launcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.roma.launcher"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":launcher"))
    implementation(project(":ui"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)

    testImplementation(libs.bundles.test.junit5)
    androidTestImplementation(libs.bundles.android.test)
}