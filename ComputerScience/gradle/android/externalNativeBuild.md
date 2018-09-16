```
android {
    defaultConfig {
    
    //配置ndk的一些规则
    externalNativeBuild {
        cmake {
            cppFlags "-frtti -fexceptions"
        }
        //配置Gradle 构建时需要的.so动态库
        // Gradle会构建那些 ABI 配置,但是只会将 defaultConfig.ndk {} 代码块中指定的配置打包到 apk 中
        ndkBuild{
            abiFilters 'armeabi','armeabi-v7a'
        }
    }
    //指打包到apk里面的.so包种类
    ndk{
        abiFilters 'armeabi','armeabi-v7a'
    }
}
```