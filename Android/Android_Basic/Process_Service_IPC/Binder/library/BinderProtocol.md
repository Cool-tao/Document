### Binder 协议  
Binder协议基本格式是（命令+数据），使用ioctl(fd, cmd, arg)函数实现交互。命令由参数cmd承载，数据由参数arg承载，随cmd不同而不同。  
BINDER_WRITE_READ   读写操作；  
BINDER_SET_MAX_THREADS   设置最大线程数；  
BINDER_SET_CONTEXT_MGR   ServiceManager专用，变成上下文管理者；  
BINDER_THREAD_EXIT  通知Binder驱动当前线程退出了；  
BINDER_VERSION  获得Binder驱动的版本号；  
