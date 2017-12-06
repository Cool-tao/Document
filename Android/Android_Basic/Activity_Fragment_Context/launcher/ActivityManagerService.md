###### ActivityManagerService

服务端对象，负责系统中所有 Activity 的生命周期。
所有的 Activity 的开启、暂停、关闭都需要 ActivityManagerService 来控制；    
在 SystemServer 进程开启的时候，就会初始化 ActivityManagerService 。  


> createSystemContext  

初始化系统上下文对象mSystemContext，并设置默认的主题,mSystemContext实际上是一个ContextImpl对象。    
调用ActivityThread.systemMain()的时候，会调用ActivityThread.attach(true)，而在attach()里面，    
则创建了Application对象，并调用了Application.onCreate()。

> 参考  

http://blog.csdn.net/itachi85/article/details/76405596  
http://blog.csdn.net/itachi85/article/details/77542286  
  


