### Gradle  
[声明依赖项](library/dependencies.md)   
常用  
include ':app'  
include ':libraries:someProject'  

[属性参考](library/BuildType_Properties.md)   
[方法参考](library/BuildType_method.md)   
[延伸方法](library/Configuration_blocks.md)  
[AppExtension](library/AppExtension.md)  
[applicationId 添加后缀](android/applicationIdSuffix.md)  
[buildTypes ](android/buildTypes.md)  
[productFlavors 构建不同产品](android/productFlavors.md)  
[dexOptions](android/dexOptions.md)  
[lintOptions](android/lintOptions.md)  
[sourceSets 修改源集](android/sourceSets.md)  
[signingConfigs 签名工具](android/signingConfigs.md)   
[applicationVariants 修改apk名字](android/applicationVariants.md)  
[compileOptions](android/compileOptions.md)   
[externalNativeBuild](android/externalNativeBuild.md)  
[repositories](library/repositories.md)  
[gradle 常见命令](library/cmd_gradle.md)  
[添加 aar](library/compile_aar.md)   
[自定义Plugin](plugin/plugin.md)   
[task](library/task.md)  
[make jar](task/makejar.md)  
[基础 方法声明与使用](library/basic_method.md)  
[基础 类声明与使用](library/basic_class.md)  
assemble  组装项目的输出的任务  
check  运行所有检查的任务  
build  这个任务将执行assemble和check  
clean  这个任务将清理项目的输出  
connectedCheck  运行需要一个已连接的设备或模拟器的检查。它们将在所有已连接的设备上并行运行  
deviceCheck  使用 API 连接到远程设备运行检查。这一个是在 CI 服务器上使用的  
compile是默认的那个，其含义是包含所有的依赖包，即在APK里，compile的依赖会存在。  
provided的意思是提供编译支持，但是不会写入apk。  
compile： main application
androidTestCompile： test application
debugCompile： debug Build Type  
releaseCompile： release Build Type  
runtime：运行时所需要的依赖。默认情况下，包含了编译时期的依赖  
testCompile：编译测试代码时所需要的依赖。默认情况下，包含了编译时产生的类文件，以及编译时期所需要的依赖  
testRuntime：测试运行时期的依赖。默认情况下，包含了上面三个时期的依赖  
branchOneCompile 'com.android.support:appcompat-v7:22.2.0'//只为branchOne添加这个依赖  

因为要构建生成一个APK，必然会有相关联的 Build Type ，APK默认配置了两个(或者更多)编译配置：compile和<buildtype>Compile。  
创建一个新的 Build Type 的时候会自动创建一个基于它名字的编译配置。   
当一个debug版本需要一个自定义库(比如报告崩溃)，但是release版本不需要或者需要一个不同版本的库的时候，会显得非常有用。  

◆ 参考  
http://www.cnblogs.com/zhaoyanjun/p/7603640.html  
http://www.flysnow.org/categories/Android/  
https://juejin.im/post/582d606767f3560063320b21  
https://www.jianshu.com/p/7b31cc80421d  
http://blog.csdn.net/linkuiyao/article/details/78079871  
http://www.bijishequ.com/subject/102  
http://blog.csdn.net/maosidiaoxian/article/details/42023609  
http://www.flysnow.org/2015/03/30/manage-your-android-project-with-gradle.html  
https://github.com/rujews/android-tech-docs/blob/master/new-build-system/user-guide/README.md  
http://tools.android.com/tech-docs/new-build-system/user-guide  
http://blog.csdn.net/maosidiaoxian/article/details/41113353  
http://gradledoc.qiniudn.com/1.12/userguide/userguide.html  
http://blog.csdn.net/maosidiaoxian/article/details/42417779  
http://blog.csdn.net/maosidiaoxian/article/details/42671999  
http://blog.csdn.net/maosidiaoxian/article/details/43148643  
https://github.com/rujews/android-gradle-book-code  


