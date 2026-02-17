@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    id("maven-publish")
    id("signing")
    alias(libs.plugins.maven.publish)
}

tasks.withType<PublishToMavenRepository> {
    val isMac = getCurrentOperatingSystem().isMacOsX
    onlyIf {
        isMac.also {
            if (!isMac) logger.error(
                """
                    Publishing the library requires macOS to be able to generate iOS artifacts.
                    Run the task on a mac or use the project GitHub workflows for publication and release.
                """
            )
        }
    }
}



mavenPublishing {
    coordinates("io.github.the-best-is-best", "compose_toast", "3.0.0")

    publishToMavenCentral(true)
    signAllPublications()

    pom {
        name.set("Compose Toast")
        description.set(
            "Compose Toast package provides a simple and customizable implementation of Toast notifications for\n" +
                    "Jetpack Compose Multiplatform applications. It allows developers to easily display transient\n" +
                    "messages to users across Android, iOS, and desktop platforms."
        )
        url.set("https://github.com/the-best-is-best/ComposeToast")
        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://opensource.org/licenses/Apache-2.0")
            }
        }
        issueManagement {
            system.set("Github")
            url.set("https://github.com/the-best-is-best/ComposeToast/issues")
        }
        scm {
            connection.set("https://github.com/the-best-is-best/ComposeToast.git")
            url.set("https://github.com/the-best-is-best/ComposeToast")
        }
        developers {
            developer {
                id.set("MichelleRaouf")
                name.set("Michelle Raouf")
                email.set("eng.michelle.raouf@gmail.com")
            }
        }
    }

}


if (project.hasProperty("signing.keyId")) {
    signing {
        useGpgCmd()
        sign(publishing.publications)
    }
}

kotlin {
    jvmToolchain(17)


    jvm()


    js {
        outputModuleName = "compose-toast"
        browser()
        binaries.library()
        generateTypeScriptDefinitions()
        compilerOptions {
            target = "es2015"
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),

    ).forEach {
        it.binaries.framework {
            baseName = "composeToast"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.jetbrains.foundation)
            implementation(libs.material3)
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)

            api(project(":toastKMP"))
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
            implementation(libs.ui)
            implementation(libs.kotlinx.coroutines.swing)
        }

        jsMain.dependencies {
            implementation(libs.html.core)

        }

        iosMain.dependencies {
        }

    }

    android {
        namespace = "io.github.the_best_is_best.composetoast"
        compileSdk = 36

//
//        defaultConfig {
//            minSdk = 23
//
//            buildFeatures {
//                //enables a Compose tooling support in the AndroidStudio
//                compose = true
//            }
//        }
    }
}


