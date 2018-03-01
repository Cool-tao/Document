在全局的 gradle  
```
allprojects {
    repositories {
        jcenter()

        flatDir {
            dirs '../build_jar_aar'
        }
    }
}
```
在模块的gradle  
```
dependencies {
    compile(name:'YibaAnalytics-release', ext:'aar')
}
```  
