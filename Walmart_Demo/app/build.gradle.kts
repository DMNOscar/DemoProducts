plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}


android {
    namespace = "com.example.walmark"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.walmark"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding =  true
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


    //RETOFIT
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //ViewModel
    implementation( "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation( "androidx.lifecycle:lifecycle-livedata:2.6.2")
    implementation( "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //Room
    implementation("androidx.sqlite:sqlite:2.1.0")
    implementation("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-migration:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    annotationProcessor ("androidx.room:room-compiler:2.4.1")

    annotationProcessor("android.arch.persistence.room:compiler:1.0.0-alpha9")
    annotationProcessor("androidx.room:room-ktx:2.3.0")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}