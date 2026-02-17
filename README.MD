<h1 align="center">Compose Toast</h1><br>

<div align="center">
  <a href="https://opensource.org/licenses/Apache-2.0">
    <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/>
  </a>
  <a href="https://android-arsenal.com/api?level=21" rel="nofollow">
    <img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" style="max-width: 100%;">
  </a>
  <img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />
  <img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" />
  <img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />
  <img src="https://img.shields.io/badge/Platform-WASM%20%2F%20JS-yellow.svg?logo=javascript" alt="Badge JS" />
  <a href="https://github.com/the-best-is-best/">
    <img alt="Profile" src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white" height="20"/>
  </a>
</div>

Compose Toast provides a simple and customizable implementation of toast notifications for **Jetpack
Compose Multiplatform** applications.  
It lets you display transient messages across **Android, iOS, JVM/desktop, JS, and WASM** from
shared Compose UI.

This repository contains:

- **`ComposeToast`**: the main Compose Multiplatform UI library (
  `io.github.the-best-is-best:compose_toast`)
- **`toastKMP`**: a lower‑level Kotlin Multiplatform module that shows **native** toasts per
  platform
- **`simple`**: sample apps demonstrating how to use the library

---

## Download

[![Maven Central – compose_toast](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_toast)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose_toast)
[![Maven Central – toast_kmp](https://img.shields.io/maven-central/v/io.github.the-best-is-best/toast_kmp)](https://central.sonatype.com/artifact/io.github.the-best-is-best/toast_kmp)

Both libraries are available on **`mavenCentral()`**.

### Gradle setup

In your top‑level `repositories`:

```kotlin
repositories {
  mavenCentral()
  google()
}
```

#### Dependency (ComposeToast, AGP 9)

```kotlin
implementation("io.github.the-best-is-best:compose_toast:2.1.2")
```

#### Dependency (ComposeToast, AGP 8)

```kotlin
implementation("io.github.the-best-is-best:compose_toast:2.1.1")
```

In a Kotlin Multiplatform project you typically add it to `commonMain`:

```kotlin
sourceSets {
  val commonMain by getting {
    dependencies {
      implementation("io.github.the-best-is-best:compose_toast:2.1.2")
    }
  }
}
```

---

### Using `toastKMP` directly (native toast only)

If you only need the **native toast abstraction** (without the Compose UI), add the `toast_kmp`
artifact:

```kotlin
dependencies {
  implementation("io.github.the-best-is-best:toast_kmp:3.0.0")
}
```

In a KMP source-set:

```kotlin
sourceSets {
  val commonMain by getting {
    dependencies {
      implementation("io.github.the-best-is-best:toast_kmp:3.0.0")
    }
  }
}
```

This exposes the low-level `NativeShowToast` APIs and helpers described in `toastKMP/README.md`.

## Basic usage (Compose MPP)

```kotlin
val stateToast = rememberAdvToastStates()
val coroutineScope = rememberCoroutineScope()

Box(modifier = Modifier.fillMaxSize()) {
  // Default styled toast overlay
  AdvToast.MakeToast(
    state = stateToast,
    toastType = EnumToastType.INFO,
    paddingBottom = 50
  )

  // Your screen content
  Column(
    modifier = Modifier
      .fillMaxSize()
      .windowInsetsPadding(WindowInsets.safeDrawing)
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ElevatedButton(
      onClick = {
        coroutineScope.launch {
          stateToast.show("Hello toast!")
        }
      }
    ) {
      Text(text = "Show Toast")
    }
  }
}
```

### Toast types

```kotlin
enum class EnumToastType {
    SUCCESS, ERROR, WARNING, INFO
}
```

Pass the desired type to `AdvToast.MakeToast` to get the corresponding styling.

---

## Custom toast UI

You can show a custom Compose layout while still using the same state object:

```kotlin
val stateCustomToast = rememberAdvToastStates()

AdvToast.MakeCustomToast(
  state = stateCustomToast,
  textColor = Color.Black,
  paddingTop = 50,
  modifier = Modifier
    .padding(horizontal = 50.dp)
    .background(Color.Cyan, shape = RoundedCornerShape(8.dp)),
  align = Arrangement.Top,
)
```

Trigger it from your UI:

```kotlin
coroutineScope.launch {
  stateCustomToast.show("Custom toast")
}
```

---

## Native toast (per‑platform)

Compose Toast also exposes a higher‑level multiplatform API that delegates to **native** toast
implementations under the hood (powered by the `toastKMP` module).

From shared code:

```kotlin
NativeShowToastCMP.show(
  "Native toast message",
  NativeToastTypeCMP.LONG
)
```

This will:

- Show an **Android** `Toast`
- Show a styled **iOS** overlay view at the bottom
- Show a **JS/WASM** toast div in the browser

For advanced/low‑level usage (e.g., calling native APIs directly from per‑platform code), see the *
*`toastKMP/README.md`**.

---

## JVM logo display

On JVM/desktop, the library can display the app logo if an image is added at:

- `resources/images/app_logo.png`

The helper APIs in `toastKMP` (`getAppLogo`/`getAppLogoIOS`) are used internally to render the logo
when present.

---

## Modules in this repository

- **`ComposeToast`** – published Compose Multiplatform UI library (
  `io.github.the-best-is-best:compose_toast`)
- **`toastKMP`** – published KMP module with platform‑specific **native** toast and app‑logo
  helpers (`io.github.the-best-is-best:toast_kmp`)
- **`simple`** – sample MPP project showing:
  - Standard toast
  - Custom toast
  - Native toast

You can open the project in **Android Studio / IntelliJ IDEA** with KMP support and run the sample
targets.

---

## License

Licensed under the **Apache License, Version 2.0**.  
See the `LICENSE` file for full details.
