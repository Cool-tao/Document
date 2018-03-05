在java代码的目录结构对应  
src/debug/java  
src/main/java  
src/main/res  
属性  
aidl	  
The Android AIDL source directory for this source set.    

assets	  
The Android Assets directory for this source set.  

compileConfigurationName	deprecated  
The name of the compile configuration for this source set.  

java	  
The Java source which is to be compiled by the Java compiler into the class output directory.  

jni	  
The Android JNI source directory for this source set.  

jniLibs	  
The Android JNI libs directory for this source set.  

manifest	   
The Android Manifest file for this source set.  

name	  
The name of this source set.  

packageConfigurationName	deprecated  
The name of the runtime configuration for this source set.  

providedConfigurationName	deprecated  
The name of the compiled-only configuration for this source set.  

renderscript	  
The Android RenderScript source directory for this source set.  

res	  
The Android Resources directory for this source set.  

resources	  
The Java resources which are to be copied into the javaResources output directory.   

方法  
setRoot(path)	  
Sets the root of the source sets to a given path. All entries of the source set are located under this root directory.   

```
android {
  ...
  sourceSets {
    // Encapsulates configurations for the main source set.
    main {
      // Changes the directory for Java sources. The default directory is
      // 'src/main/java'.
      java.srcDirs = ['other/java']

      // If you list multiple directories, Gradle uses all of them to collect
      // sources. Because Gradle gives these directories equal priority, if
      // you define the same resource in more than one directory, you get an
      // error when merging resources. The default directory is 'src/main/res'.
      res.srcDirs = ['other/res1', 'other/res2']

      // Note: You should avoid specifying a directory which is a parent to one
      // or more other directories you specify. For example, avoid the following:
      // res.srcDirs = ['other/res1', 'other/res1/layouts', 'other/res1/strings']
      // You should specify either only the root 'other/res1' directory, or only the
      // nested 'other/res1/layouts' and 'other/res1/strings' directories.

      // For each source set, you can specify only one Android manifest.
      // By default, Android Studio creates a manifest for your main source
      // set in the src/main/ directory.
      manifest.srcFile 'other/AndroidManifest.xml'
      ...
    }

    // Create additional blocks to configure other source sets.
    androidTest {

      // If all the files for a source set are located under a single root
      // directory, you can specify that directory using the setRoot property.
      // When gathering sources for the source set, Gradle looks only in locations
      // relative to the root directory you specify. For example, after applying the
      // configuration below for the androidTest source set, Gradle looks for Java
      // sources only in the src/tests/java/ directory.
      setRoot 'src/tests'
      ...
      
    }
  }
}

```