<h1 align="center">Compose Toast</h1><br>

<div align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href="https://android-arsenal.com/api?level=21" rel="nofollow">
    <img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" style="max-width: 100%;">
</a>
<img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />
		<img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" />
		<img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />
    <img src="https://img.shields.io/badge/Platform-WASM%20%2F%20JS-yellow.svg?logo=javascript" alt="Badge JS" />
<a href="https://github.com/the-best-is-best/"><img alt="Profile" src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white" height="20"/></a>

</div>


Compose Toast package provides a simple and customizable implementation of Toast notifications for
Jetpack Compose Multiplatform applications. It allows developers to easily display transient
messages to users across Android, iOS, and desktop platforms.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_toast)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose_toast)

Compose Toast available on `mavenCentral()`.

```kotlin
implementation("io.github.the-best-is-best:compose_toast:1.0.2")
```

## Setup add this in AndroidMain

```kotlin
AndroidLogoToast.initialization(this)
// if use native to need
AndroidNativeShowToast.initialization(this)

```

## How to use

```kotlin
     val stateToast = rememberAdvToastStates()

val coroutineScope = rememberCoroutineScope()

...

Box(modifier = Modifier.fillMaxSize()) {
    AdvToast.MakeToast(state = stateToast, toastType = EnumToastType.INFO, paddingBottom = 50)
}{
    .....

    ElevatedButton(onClick = {
    coroutineScope.launch {
        stateToast.show("Hello $name! Toast")
    }
}) {
    Text(text = "Show Toast")
}
}
...
 ```

### Types

```kotlin

enum class EnumToastType {
    SUCCESS, ERROR, WARNING, INFO
}
```

#### How make native toast

```kotlin
// work on ios and android only
NativeShowToast.show(
    "Native toast Native toast Native toast Native toast Native toast",
    NativeToastType.LONG
)

```
