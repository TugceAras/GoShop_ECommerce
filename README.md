# <p align="center"> GOSHOP E-Commerce App 🛍 </p>

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://user-images.githubusercontent.com/79931228/222906642-8ec4d012-5857-4b1b-8cb1-10f4499f5cab.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906633-d30aa1c6-30b2-41ca-81fb-a7193ba8b098.png"/> 
  <img src="https://user-images.githubusercontent.com/79931228/222906635-75a7dca0-da86-4e11-aef6-840136d1a63e.png"/> 
  <img src="https://user-images.githubusercontent.com/79931228/222906636-1f4b3dd2-2bc1-4812-ae29-48fd17cd1a2f.png"/> <br>
  <img src="https://user-images.githubusercontent.com/79931228/222906632-f5a26a2e-fd65-4a96-89ac-8fc03b390c9c.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906639-c590f61e-1973-45bd-a280-a45fab6eb781.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906630-0c6439e4-46e2-4686-a18a-465cb57d03e6.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906631-c38bac5d-f54a-4db4-a5d6-27876a1d989b.png"/> <br>
  <img src="https://user-images.githubusercontent.com/79931228/222906628-bccf731c-c7d7-4c45-b0ce-7d86cc3fc904.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906637-5a8769c8-e4b9-4d97-b4c6-d9aa53054d5b.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906645-08c77e1f-5290-4587-a08e-c946d5a64aee.png"/>
  <img src="https://user-images.githubusercontent.com/79931228/222906641-687babac-085f-43c9-8ec3-f6cbd7c833d2.png"/>
</p>

<br>

## 📽 Video
<!-- Video -->
<div align="left">
  <video src="https://user-images.githubusercontent.com/79931228/222907198-e5b84946-4d9e-4474-b65a-7252467ad830.mp4"/>
</div>

<br>

<!-- Technologies -->
## :point_down: Structures Used
- MVVM
- Navigation
- Dependency Injection | Hilt
- Coroutine
- Retrofit
- Room
- View Binding | Data Binding
- Parcelable
- Glide

For animation : Lottie used

<br>

## :pencil2: Dependency
```
    // Navigation
    def nav_version = "2.5.3"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Coroutine
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Coroutine Scope
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'

    // Room
    def room_version = "2.5.0"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    kapt  'com.github.bumptech.glide:compiler:4.14.2'

    // DI-Hilt
    implementation "com.google.dagger:hilt-android:2.44"
    kapt "com.google.dagger:hilt-compiler:2.44"

    // Lottie
    implementation 'com.airbnb.android:lottie:5.2.0'
```

app build.gradle:

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs.kotlin'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

buildFeatures {
      viewBinding true
      dataBinding true
 }
```
project build.gradle:

```
plugins {
    id 'com.android.application' version '7.4.2' apply false
    id 'com.android.library' version '7.4.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.10' apply false
    id 'androidx.navigation.safeargs' version '2.5.3' apply false
    id 'com.google.dagger.hilt.android' version '2.44' apply false
}
```

<!-- Manifest File -->
## :exclamation: Manifest File
```
<uses-permission android:name="android.permission.INTERNET" />
```

<!-- API -->
## :point_down: API
- https://fakestoreapi.com/
