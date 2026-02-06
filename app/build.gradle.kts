import org.jetbrains.kotlin.fir.declarations.builder.buildScript
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.serialization)

}

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

val url =localProperties["url"] as String
val apiKey = localProperties["API_KEY"] as String

android {
    // ...

}

android {
    namespace = "com.example.venturenest"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.venturenest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "url", "\"$url\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true

            buildConfig = true
        
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
  //coil for images
    implementation(libs.coil.compose)

   //material icons
    implementation (libs.androidx.material.icons.extended.android)
    //bottombar library
    implementation ("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")
  //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    // ViewModel and LiveData for MVVM architecture
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.3.1")

   //dagger-Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation ("com.google.android.gms:play-services-auth:20.5.0")  // Ensure this is added
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.navigation.compose)

    //Lottie
    implementation ("com.airbnb.android:lottie-compose:6.5.2")

    val nav_version = "2.8.0"
//navigation

   //chartsLib
    implementation("io.github.thechance101:chart:Beta-0.0.5")
//    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.32.0")

    implementation("androidx.room:room-runtime:2.6.1") // Use latest version
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0") // Gemini AI SDK
    implementation("com.google.code.gson:gson:2.10.1")


}

kapt {
    correctErrorTypes = true
    javacOptions {
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED")
        option("--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED")
    }
}