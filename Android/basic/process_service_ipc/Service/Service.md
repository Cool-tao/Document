#### Service  

> Service的基本描述  

服务是运行在后台的、没有UI的组件，必须要在清单文件注册；  
服务运行在主线程；  
服务的启动方式有两种： bind 和 start；  
服务的寄存方式有两种：LocalService 和 RemoteService；

> Service的详细了解  

[Service在清单文件](manifest/Service.md)  
[什么是远程服务](RemoteService/RemoteService.md)  
[bind远程服务](RemoteService/RemoteService.md)  
[前台服务](ForegroundService/ForegroundService.md)  
[Service与Thread](Service_Thread.md)  
[启动一个服务，服务的生命周期](Service_lifecycle.md)  
[向Service传递参数](fun/start_params.md)    
[onDestroy](fun/onDestroy.md)  
[onStartCommand](fun/onStartCommand.md)  

##### 参考  

关于Android Service真正的完全详解，你需要知道的一切  
http://blog.csdn.net/javazejian/article/details/52709857  

全面剖释 Service 服务  
https://www.cnblogs.com/leslies2/p/5401813.html  

保活Service  
http://www.jianshu.com/p/28c5377c77c4  
https://www.jianshu.com/p/c1a9e3e86666      

启动前台服务，国内产商的配合程度  
http://www.jianshu.com/p/76af1e7503a5  
Process(进程)  
https://github.com/AlanCheen/Android-Resources/blob/master/Process.md  

