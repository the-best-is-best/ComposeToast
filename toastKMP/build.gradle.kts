import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
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
    coordinates("io.github.the-best-is-best", "toast_kmp", "3.0.0")

    publishToMavenCentral(true)
    signAllPublications()

    pom {
        name.set("Compose Toast")
        description.set(
            "Toast KMP package provides a simple and customizable implementation of Toast notifications for\n" +
                    "for native apps and web applications. It allows developers to easily display transient\n" +
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

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "io.github.the_best_is_best.toast_kmp"
        compileSdk {
            version = release(36) { minorApiLevel = 1 }
        }
        minSdk = 21

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "toastKMPKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = xcfName

        }
    }

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
    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
            }
        }


        jvm()

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
                implementation(libs.androidx.startup.runtime)
                implementation(libs.runtime)
                implementation(libs.ui)
                implementation(libs.androidx.core.ktx)
            }
        }


        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }

        jsMain.dependencies {


        }

        wasmJsMain.dependencies {
            implementation(kotlin("stdlib-wasm-js"))

            // للوصول إلى MainScope و launch و delay
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.browser)
        }
        getByName("jsMain") {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
            }
        }
    }

}