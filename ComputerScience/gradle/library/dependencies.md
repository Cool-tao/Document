compile fileTree(dir: 'libs', include: ['*.jar'])  
compile 'com.android.support:appcompat-v7:25.0.0'    
compile project(':YibaAnalytics')    
compile project(':library:YibaAnalytics')    
compile files('libs/YibaAnalytics5.jar')    

debugCompile project(path: ':library', configuration: 'debug')  
releaseCompile project(path: ':library', configuration: 'release')    

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

api  自己用，也暴露给 别人用  
implementation  仅仅自己用  

    
