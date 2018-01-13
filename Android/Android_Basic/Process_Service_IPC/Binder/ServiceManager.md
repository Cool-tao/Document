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
