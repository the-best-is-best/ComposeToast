<h2 align="center">toastKMP</h2>

`toastKMP` is a **Kotlin Multiplatform** module that provides:

- Simple **native toast APIs** for each supported platform
- Small helpers to access the **application icon/logo** where possible

It is used internally by the `ComposeToast` library, but you can also reuse it in other KMP projects
as a low‑level native toast layer.

---

## Supported platforms

- **Android** – wraps the platform `Toast` API
- **iOS** – draws a custom bottom toast `UIView` (optionally with app logo)
- **JVM (via ComposeToast)** – uses shared helpers (e.g., app logo)
- **JS (browser)** – renders toast elements into the DOM
- **WASM JS** – also uses DOM to render toast elements

---

## Module overview

Key APIs (per platform):

- **Android**
    - `NativeShowToast.show(msg: String, type: NativeToastType)`
    - `enum class NativeToastType { SHORT, LONG }`
    - `getAppLogo(): ImageBitmap?` – gets the app icon as `ImageBitmap` using the current
      `applicationContext`

- **iOS**
    - `NativeShowToast.show(msg: String, type: NativeToastType)`
    - `enum class NativeToastType { SHORT, LONG }`
    - `getAppLogoIOS(): UIImage?` – reads the primary icon from the app bundle and returns it as a
      `UIImage`

- **JS (Kotlin/JS)**
    - `NativeShowToast.show(msg: String, type: NativeToastType)` – shared enum
      `NativeToastType { SHORT, LONG }`
    - Also exposed as:
        - `@JsExport enum class NativeToastTypeJs { SHORT, LONG }`
        - `@JsExport object NativeShowToastJs { fun show(msg: String, type: NativeToastTypeJs) }`
    - Renders styled toast `<div>` elements into the page and cleans them up automatically

- **WASM JS**
    - `NativeShowToast.show(msg: String, type: NativeToastType)`
    - Uses the browser DOM from WASM/JS to render and remove toast elements

---

## Usage inside this repository

The `ComposeToast` module depends on `toastKMP`:

```kotlin
commonMain {
    dependencies {
        api(project(":toastKMP"))
    }
}
```

Higher‑level APIs in `ComposeToast` such as:

```kotlin
NativeShowToastCMP.show(
    "Native toast Native toast Native toast Native toast Native toast",
    NativeToastTypeCMP.LONG
)
```

delegate to the concrete implementations in `toastKMP` for each platform.

If you only need the **native toast behavior** and not the full Compose UI, you can:

- Keep `toastKMP` as a separate module in your KMP project.
- Call the platform APIs (`NativeShowToast` / `NativeShowToastJs` / `NativeShowToast` for WASM)
  directly from your platform code.

---

## Gradle configuration (module)

`toastKMP` itself is defined as a KMP library with:

- `androidLibrary { ... }` target
- iOS targets (`iosX64`, `iosArm64`, `iosSimulatorArm64`) with a shared framework base name
  `toastKMPKit`
- `js` target with `browser()` and `binaries.library()` plus TypeScript definitions
- `wasmJs` target with `browser()` and `binaries.executable()`

Common dependencies are minimal (Kotlin stdlib, coroutines/browser where needed) so you can embed
this module in your own project without heavy transitive weight.

---

## When to use `toastKMP` directly

Use `toastKMP` directly when:

- You are not using Compose UI but still want a **simple native toast abstraction** across
  platforms.
- You want to integrate the native toast behavior into an existing KMP architecture and keep UI
  concerns separate.
- You need to customize or fork the native toast implementations while keeping your own shared API
  surface.

For most Compose Multiplatform apps, using **`io.github.the-best-is-best:compose_toast`** is
recommended, which already integrates `toastKMP` under the hood.

