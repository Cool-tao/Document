##### 在全局的 gradle  
```
allprojects {
    repositories {
        mavenLocal()
        jcenter()
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }

        /*设置 aar 路径 */
        flatDir {
            dirs '../build_jar_aar'
        }
    }

    configurations.all {
        /*解决  Android dependency 'android.arch.core:runtime' has different version for the*/
        resolutionStrategy.force libs.arch_core_common
        resolutionStrategy.force libs.arch_core_runtime
        resolutionStrategy.force libs.support_annotation
    }
}
```