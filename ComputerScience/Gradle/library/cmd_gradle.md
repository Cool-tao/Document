### Gradle 常见命令  
windows  gradlew;  mac  gradle  
//构建  
gradlew app:clean    //移除所有的编译输出文件，比如apk    

gradlew app:build   //构建 app module ，构建任务，相当于同时执行了check任务和assemble任务  

//检测  
gradlew app:check   //执行lint检测编译。  

//打包  
gradlew app:assemble //可以编译出release包和debug包，可以使用gradlew assembleRelease或者gradlew assembleDebug来单独编译一种包   

gradlew app:assembleRelease  //app module 打 release 包   

gradlew app:assembleDebug  //app module 打 debug 包  

//安装，卸载  
gradlew app:installDebug  //安装 app 的 debug 包到手机上  

gradlew app:uninstallDebug  //卸载手机上 app 的 debug 包   

gradlew app:uninstallRelease  //卸载手机上 app 的 release 包   

gradlew app:uninstallAll  //卸载手机上所有 app 的包   

gradlew assembleWandoujiaRelease  //豌豆荚 release 包  

gradlew assembleWandoujiaDebug //豌豆荚 debug 包  