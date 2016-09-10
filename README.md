# WeatherApp

Demo Weather app to test various Android concepts like MVP, RxJava, Dagger 2, Testing

<p align="center">
    <img src="images/screens.png" alt="Screenshots"/>
</p>

The architecture of this app was based upon https://github.com/ribot/android-guidelines/blob/master/architecture_guidelines/android_architecture.md with custom modifications like not using an Eventbus as this is not needed in an Rx flow based app.

## Libraries

The libraries and tools used include:

- Support library
- RecyclerViews and CardViews
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrofit](http://square.github.io/retrofit/) and [OkHttp](https://github.com/square/okhttp)
- [Dagger 2](http://google.github.io/dagger/)
- [EasyAdapter](https://github.com/ribot/easy-adapter)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Espresso](https://google.github.io/android-testing-support-library/)
- [Mockito](http://mockito.org/)

## Requirements

- [Android SDK](http://developer.android.com/sdk/index.html).
- Android [4.2 (API 17) ](http://developer.android.com/tools/revisions/platforms.html#6.0).
- Android SDK Tools
- Android SDK Build tools 23.0.1
- Android Support Repository

## Build Instructions

In order to run this project, you'll need to setup several things beforehand:

- Our application uses the [OpenWeatherMap API](http://openweathermap.org) to obtain information about current weather and forecasts,
you'll need to register and obtain an API Key

- You'll need to set the values found in the [gradle.properties](gradle.properties) file.
This involves the OpenWeatherMap Api Key (`apiToken`)

# Licence

```
Copyright 2016 Alexandros Koufatzis.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limi
