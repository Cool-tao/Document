### 本地仓库  
minePluginUri = file:///Users/alex/WorkSpace/Gradle/MinePlugin/repo/  

◆ provide library  
项目 gradle  
```
buildscript {
    repositories {
        maven { url minePluginUri }
    }    
}
allprojects {
    repositories {
        maven { url minePluginUri }
    }
}
```
module 的 gradle  
```
apply plugin: 'com.android.library'
apply plugin: 'maven'
android {
    compileSdkVersion 27
    defaultConfig {
    }
    buildTypes {
    }
}
dependencies {
}

uploadArchives {
    repositories {
        mavenDeployer {
            //设置插件的GAV参数
            pom.groupId = 'org.alex.basic'
            pom.artifactId = 'basicLibrary'
            pom.version = '1.0.0'
            //文件发布到下面目录
            repository(url: uri(minePluginUri))
        }
    }
}

```
◆ using library  
项目 gradle  
```
buildscript {
    repositories {
        maven { url minePluginUri }
    }    
}
allprojects {
    repositories {
        maven { url minePluginUri }
    }
}
```
module 的 gradle  
```
apply plugin: 'com.android.application'
android {
    compileSdkVersion 27
    defaultConfig {
     }
    buildTypes {
    }
}

dependencies {
    compile 'org.alex.basic:basicLibrary:1.0.0@aar'
}

```