#### Service  

> Service的基本描述  

服务是四大组件之一，必须要在清单文件注册；  
服务运行在主线程；  
服务的启动方式有两种： bind 和 start；  
服务的寄存方式有两种：LocalService 和 RemoteService；

> Service的详细了解  

[远程Service](RemoteService/RemoteService.md)： 将一个普通的Service转换成远程Service，只需要在注册Service添加属性 android:process=":remote" 即可；  
[Service与Thread](Service_Thread.md)  
[服务的生命周期](Service_lifecycle.md)；  
[向Service传递参数](fun/start_params.md)    
[onDestroy](fun/onDestroy.md)  
[onStartCommand](fun/onStartCommand.md)  

> 参考  

http://blog.csdn.net/javazejian/article/details/52709857  
Service生命周期最全面解析  http://www.jianshu.com/p/8d0cde35eb10  
Service史上最全面解析  http://www.jianshu.com/p/d963c55c3ab9  
远程服务Service（含AIDL & IPC讲解）  http://www.jianshu.com/p/34326751b2c6  
（本地、可通信的、前台、远程）Service使用全面介绍  http://www.jianshu.com/p/e04c4239b07e  
IntentService用法&源码  http://www.jianshu.com/p/8a3c44a9173a  
https://www.cnblogs.com/leslies2/p/5401813.html  
http://chuansong.me/n/504099451432  
http://www.jianshu.com/p/de868fadedda  
http://www.jianshu.com/p/28c5377c77c4  
http://www.jianshu.com/p/5505390503fa  
http://www.jianshu.com/p/4a83becd758e  
http://www.jianshu.com/p/ca00608a8a08  
http://www.jianshu.com/p/ced404f406b8  






