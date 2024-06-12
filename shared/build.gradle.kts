plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kmpNativeCoroutines)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget()
    jvmToolchain(11)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines)
            implementation(libs.ktor.client)
            implementation(libs.ktor.content)
            implementation(libs.ktor.serialization)
            api(libs.koin)
            api(libs.gson)
            api(libs.rickclephas.viewmodel)
        }
        commonTest.dependencies {
//            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            api(libs.koin.android)
        }
        iosMain.dependencies{
            implementation(libs.ktor.ios)
        }
        all {
            languageSettings {
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
    }
}

android {
    namespace = "me.arunajayan.getmovies"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
}
