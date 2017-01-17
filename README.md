# WheelView 滚动选择器
通过对 WheelView 的选择组合，可以实现单级以及多级滑动选择：比如时间、省市等。
## 示例图
<img width="320" height="548" src="https://github.com/LidongWen/WheelView/blob/master/img/GIF.gif"></img>

## 引用
```groovy
// 项目引用
dependencies {
    compile 'com.github.LidongWen:WheelView:1.0.4'
}

// 根目录下引用
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.0'
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        maven { url "https://www.jitpack.io" }
    }
}
```

