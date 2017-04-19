# WaterLoadingView

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-WaterLoadingView-green.svg?style=true)](https://android-arsenal.com/details/1/2908)
[![Travis](https://img.shields.io/travis/rust-lang/rust.svg)]()
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)  

**WaterLoadingView** - An Android library that provides a realistic water-loading effect.

## Sample

<img src="http://7xivx9.com1.z0.glb.clouddn.com/waterloadingview-demo.gif" alt="sample" title="sample" width="200" height="380" />

## Usage

**For a working implementation of this project see the `app/` folder.**

### Step 1

Include the library as a local library project or add the dependency in your build.gradle.

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    compile 'com.github.icodeu:WaterLoadingView:v1.1'
}
```

Or

Import the library, then add it to your /settings.gradle and /app/build.gradle.


### Step 2

Include the WaterLoadingView widget in your layout. And you can customize it like this.

```xml
<com.icodeyou.library.WaterLoadingView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:waterColor="#A2D6AE"
        app:waterFallVelocity="20"
        app:waterShakeVelocity="20" />
```

## Customization

Please feel free to :)

|name|format|description|
|:---:|:---:|:---:|
| waterColor | color | The water color, default is #A2D6AE, which is like green
| waterFallVelocity | integer | Velocity of water fall down, default is 50, advice range of 0~100
| waterShakeVelocity | integer | Shake effect in horizontal, default is 20, advice range of 0~100

**All attributes have their respective getters and setters to change them at runtime.**

## Contact Me

Born in 1992, graduated from BJTU University, now a developer of Android in Baidu. Loving technology, programming, reading books.

## License

    Copyright 2017 icodeyou

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
