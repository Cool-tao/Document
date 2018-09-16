```
android {
    buildTypes {
        debug {
            applicationIdSuffix ".debug"
        }

        jnidebug.initWith(buildTypes.debug)
        jnidebug {
            packageNameSuffix ".jnidebug"
            jniDebuggable true
        }
    }
}
```