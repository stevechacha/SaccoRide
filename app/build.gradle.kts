plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}


android {
    namespace= "com.dev.chacha.saccoride"
    compileSdk =33

    defaultConfig {
        applicationId ="com.dev.chacha.saccoride"
        minSdk =24
        targetSdk =33
        versionCode= 1
        versionName ="1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary= true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            pickFirsts.add("META-INF/io.netty.versions.properties")
            pickFirsts.add("META-INF/INDEX.LIST")
        }
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    implementation(libs.android.coreKtx)
    implementation(libs.android.appCompat)
    implementation(libs.android.material)

    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    implementation(libs.timber)

    androidTestImplementation(libs.android.test.junit4)
    androidTestImplementation(libs.android.test.espresso)
    androidTestImplementation(libs.android.hilt.testing)
    kaptAndroidTest(libs.android.hilt.compiler)

    testImplementation(libs.test.junit4)
    testImplementation(libs.android.hilt.compiler)
    kaptTest(libs.android.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // SystemUi
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.25.1")

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")

}