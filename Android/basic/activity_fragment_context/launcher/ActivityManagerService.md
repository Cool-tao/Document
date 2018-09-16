### ActivityManagerService  
SystemServer 进程 #run 方法 → #startBootstrapServices 方法 →  启动 ActivityManagerService；  
在SystemService.java 的run方法，ActivityManagerService向Native的ServiceManager注册服务；  
ActivityManagerService是服务端对象，负责系统中所有 Activity 的生命周期；  
创建ActivityManager、ProcessRecord、ServiceManager；  
start方法中，反射调用 ActivityThread.main方法；  
◆ createSystemContext  
初始化系统上下文对象mSystemContext，并设置默认的主题,mSystemContext实际上是一个ContextImpl对象。    
调用ActivityThread.systemMain()的时候，会调用ActivityThread.attach(true)，而在attach()里面，    
则创建了Application对象，并调用了Application.onCreate()。  

◆ 参考  
http://blog.csdn.net/itachi85/article/details/76405596  
http://blog.csdn.net/itachi85/article/details/77542286  
  


