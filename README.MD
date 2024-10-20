<h1 align="center">Compose Toast</h1><br>

<div align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href="https://android-arsenal.com/api?level=21" rel="nofollow"><img alt="API" src="https://camo.githubusercontent.com/0eda703da08220e08354f624a3fc0023f10416a302565c69c3759bf6e0800d40/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4150492d32312532422d627269676874677265656e2e7376673f7374796c653d666c6174" data-canonical-src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" style="max-width: 100%;"></a>
<a href="https://github.com/the-best-is-best/"><img alt="Profile" src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white" height="20"/></a>
<a href="https://central.sonatype.com/search?q=io.github.the-best-is-best&smo=true"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_toast"/></a>
</div>

Compose Toast package provides a simple and customizable implementation of Toast notifications for
Jetpack Compose Multiplatform applications. It allows developers to easily display transient
messages to users across Android, iOS, and desktop platforms.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_toast)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose_toast)

Compose Date Time Picker is available on `mavenCentral()`.

```kotlin
implementation("io.github.the-best-is-best:compose_toast:1.0.1")
```

## Setup

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
