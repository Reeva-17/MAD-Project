plugins {
    alias(libs.plugins.android.application)
<<<<<<< HEAD
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.trustlens"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.trustlens"
=======
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.madfirebase"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.madfirebase"
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
<<<<<<< HEAD

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
=======
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
    }

    buildTypes {
        release {
            isMinifyEnabled = false
<<<<<<< HEAD
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }

=======
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
}

dependencies {
    implementation(libs.androidx.core.ktx)
<<<<<<< HEAD
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.navigation:navigation-compose:2.9.7")
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

=======
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // ✅ Firebase (WORKING SETUP)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-database")

    testImplementation(libs.junit)
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
}