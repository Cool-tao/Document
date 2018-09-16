###### 65535问题

[对MultiDex 65535 的认识](multidex/MultiDex.md)  

> app gradle
```
android {

    defaultConfig {
        multiDexEnabled = true
    }
    
    dexOptions {
        preDexLibraries = false
        dexInProcess = false
        javaMaxHeapSize "4g"
    }  
}
```

> Application
```
override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(base)
}
```