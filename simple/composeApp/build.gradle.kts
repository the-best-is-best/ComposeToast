@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    jvmToolchain(17)

    jvm()


    js {
        browser()
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.jetbrains.foundation)
            implementation(libs.material3)
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)

            implementation(project(":ComposeToast"))
        }

//        commonTest.dependencies {
//            implementation(kotlin("test"))
//            @OptIn(ExperimentalComposeLibrary::class)
//            implementation(compose.uiTest)
//        }

        androidMain.dependencies {
            implementation(libs.ui.tooling)
            implementation(libs.androidx.activityCompose)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        iosMain.dependencies {
        }
        jsMain.dependencies {
            implementation(compose.runtime)
        }

    }



    android {
        namespace = "io.github.sample"
        compileSdk = 36
        minSdk = 23


//        defaultConfig {
//            minSdk = 23
//            targetSdk = 36
//
//            applicationId = "org.company.app.androidApp"
//            versionCode = 1
//            versionName = "1.0.0"
//
//            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        }
    }


}



compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeApp"
            packageVersion = "1.0.0"

            linux {
                iconFile.set(project.file("src/jvmMain/resources/desktopAppIcons/LinuxIcon.png"))
            }
            windows {
                iconFile.set(project.file("src/jvmMain/resources/desktopAppIcons/WindowsIcon.ico"))
            }
            macOS {
                iconFile.set(project.file("src/jvmMain/resources/desktopAppIcons/MacosIcon.icns"))
                bundleID = "org.company.app.desktopApp"
            }
        }
    }
}
