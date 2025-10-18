import org.gradle.kotlin.dsl.androidTestImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.nativeuitesting"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.nativeuitesting"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }
    testOptions {
        animationsDisabled = true // Recommended for stable tests
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.espresso.contrib)
    implementation(libs.androidx.uiautomator)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    androidTestImplementation("androidx.test:core:1.7.0")
    androidTestImplementation("androidx.test:core-ktx:1.7.0")

    // AndroidX Test - Runner
    androidTestImplementation("androidx.test:runner:1.7.0")

    // AndroidX Test - Rules
    androidTestImplementation ("androidx.test:rules:1.7.0")

    // JUnit 4
    androidTestImplementation (libs.junit)

    // AndroidX Test - JUnit extensions
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Espresso Contrib (RecyclerView, DrawerLayout, Picker, etc.)
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.7.0")

    // Espresso Idling Resource (REQUIRED for WaitActions)
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.7.0")
    androidTestImplementation("org.hamcrest:hamcrest:2.2")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0") {
        exclude(group = "org.hamcrest", module = "hamcrest-core")
        exclude(group = "org.hamcrest", module = "hamcrest-library")
        exclude(group = "org.hamcrest", module = "hamcrest-integration")
    }
}