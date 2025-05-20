import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

android {
    namespace = "eu.epfc.tmdb"
    compileSdk = 35

    defaultConfig {

        val keystoreFile  = project.rootProject.file("keystore.properties")
        val keystoreProperties = Properties()
        keystoreProperties.load(keystoreFile.inputStream())
        val tmdbApiKey= keystoreProperties["TMDB_API_KEY"] ?: ""
        val tmdbAccessToken= keystoreProperties["TMDB_ACCESS_TOKEN"] ?: ""

        applicationId = "eu.epfc.tmdb"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            type = "String" ,
            name = "TMDB_ACCESS_TOKEN" ,
            value = tmdbAccessToken as String
        )

        buildConfigField(
            type = "String" ,
            name = "TMDB_API_KEY" ,
            value = tmdbApiKey as String
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)
    //moshi
    implementation(libs.squareup.moshi.kotlin)
    implementation (libs.moshi)
    implementation (libs.converter.moshi)
    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}