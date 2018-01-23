### ServiceManager  
ServiceManager对应 service_manager.c文件；  
在init进程中，ServiceManager被启动，一旦ServiceManager发生重启，Zygote、media、surfaceflinger等都会被重新加载；  
在service_manager.c 的main函数，调用binder_become_context_manager 函数，将自己设置为binder的上下文管理者，之后进入循环，等待客户端的请求；  
进入循环体之后，永远不会退出循环，除非发生致命错误，并调用binder_parser来解析处理消息；  
◆ ServiceManager的功能  
◑ 注册  
当创建Binder Server就要向ServiceManager注册；  
◑ 查询  
应用程序可以向ServiceManager发起请求，获取某个Binder Server的句柄；  
◑ 其他信息查询  
诸如ServiceManager的版本号、当前状态等；  

◆ Client 获得实名Binder的引用  
Server向SMgr注册了Binder实体及其名字后，Client就可以通过名字获得该Binder的引用了。  
从面向对象的角度，这个Binder对象现在有了两个引用：一个位于SMgr中，一个位于发起请求的Client中。  
如果接下来有更多的Client请求该Binder，系统中就会有更多的引用指向该Binder，就象java里一个对象存在多个引用一样。    

◆ 匿名 Binder  
并不是所有Binder都需要注册给SMgr广而告之的。Server端可以通过已经建立的Binder连接将创建的Binder实体传给Client，  
当然这条已经建立的Binder连接必须是通过实名Binder实现。由于这个Binder没有向SMgr注册名字，所以是个匿名Binder。  
Client将会收到这个匿名Binder的引用，通过这个引用向位于Server中的实体发送请求。  
匿名Binder为通信双方建立一条私密通道，只要Server没有把匿名Binder发给别的进程，  
别的进程就无法通过穷举或猜测等任何方式获得该Binder的引用，向该Binder发送请求。  
