plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.ramanshsharma07.animated_nav_bar"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    // 2. 👇 ADD THIS BUILD FEATURES BLOCK
    buildFeatures {
        // This tells the Android plugin to enable Compose for this module
        compose = true
    }

    // 3. 👇 ADD THIS COMPOSE OPTIONS BLOCK
    // This links the compiler version to the BOM, ensuring they are always compatible
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    api(libs.androidx.core.ktx)
//    api(libs.ui)
//    api(libs.material3)
//    api(libs.ui.tooling.preview)
    api(libs.androidx.navigation.compose.v294)

    api(platform(libs.androidx.compose.bom))

    api(libs.androidx.compose.foundation)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling.preview)


//    implementation(libs.androidx.compose.compiler.compiler)
}