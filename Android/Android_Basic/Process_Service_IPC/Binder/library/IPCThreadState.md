IPCThreadState也是以单例模式设计的。  
由于每个进程只维护了一个ProcessState实例，同时ProcessState只启动一个Pool thread，也就是说每一个进程只会启动一个Pool thread，  
因此每个进程则只需要一个IPCThreadState即可。  
Pool thread的实际内容则为：  
IPCThreadState::self()->joinThreadPool();  
  
    