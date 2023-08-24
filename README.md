# Android Phase

## Table of Contents
* [About The Library](#about-the-library)
* [Adding The Dependency](#adding-the-dependency)
* [Example Use](#example-use)
* [License](#license)
* [Contact](#contact)

## About The Library
An Android library to hold any data within states.
For the example use of this library, you can look at my [Movee](https://github.com/mutkuensert/Movee) project on Github.
If you want, you can also use this class [GetPhaseFlow](https://gist.github.com/mutkuensert/11093b3c8b48fa130b0b35bcb5e9faf1).

## Adding The Dependency
Add jitpack into the repositories

```gradle
maven { url 'https://jitpack.io' }
```

Add the dependency in build.gradle file.
```gradle
implementation 'com.github.mutkuensert:Android-Phase:v1.1.0.1'
```

**You can also add the library locally. To do that:**

Clone the project.

Run in the terminal:
```gradle
./gradlew build
```
Find generated aar file under build folder and copy & paste it into your project. You can create a directory called "libs" under your app module and paste the file into there.
Then you can add the dependency:

```gradle
implementation files('libs/AndroidPhase-release.aar')
```

## Example Use
```kotlin
suspend fun getTvShowDetails(tvShowId: Int): Phase<TvShowDetails> {
    val response = tvShowsApi.getTvShowDetails(tvShowId)

    return if (response.isSuccessful) {
        Phase.Success(data = response.body()?.mapToDomainModel())
    } else {
        Phase.Error(message = "Unsuccessful response.")
    }
}
```

```kotlin
@Composable
fun TvShowDetails(
    phase: Phase<TvShowDetails>,
    loadTvCastIfSuccessful: () -> Unit
) {
    phase.Execute(
        onLoading = { Loading() },
        onSuccess = {
            if (data != null) {
                TvDetailsItem(data!!)
                LaunchedEffect(Unit) { loadTvCastIfSuccessful() }
            }
        },
        onError = { LocalContext.current.showToastIfNotNull(message) })
}
```


## License
```xml
Copyright 2023 Mustafa Utku Ensert

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Contact
[ensertyazilim@gmail.com](#)
