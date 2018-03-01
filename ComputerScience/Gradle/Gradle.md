### Gradle  

常用  
include ':app'  
include ':libraries:someProject'  
compile 'com.android.support:appcompat-v7:25.0.0'  
compile project(':YibaAnalytics')  
compile files('libs/YibaAnalytics5.jar')  

[repositories](library/repositories.md)  
[gradle 常见命令](library/cmd_gradle.md)  
[添加 aar](library/compile_aar.md)   

compile是默认的那个，其含义是包含所有的依赖包，即在APK里，compile的依赖会存在。  
provided的意思是提供编译支持，但是不会写入apk。  

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

