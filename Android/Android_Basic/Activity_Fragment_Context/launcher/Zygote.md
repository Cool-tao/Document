### Zygote 进程  
[Android是基于Linux系统的，当手机开机的时候，Linux的内核加载完成之后就会启动一个“init“的进程](fork_Zygote.md)，   
在Linux系统中，所有的进程都是由init进程fork出来的，zygote进程也不例外。  
在Android系统中，所有的应用程序进程以及系统服务进程SystemServer，都是由Zygote进程fork出来的，都是zygote的子进程。  
◆ Zygote启动流程就讲到这，Zygote进程共做了如下几件事：   
◐ 解析init.zygote.rc中的参数，创建AppRuntime并调用AppRuntime.start()方法；  
◐ 调用AndroidRuntime的startVM()方法创建虚拟机，再调用startReg()注册JNI函数；     
◐ 通过JNI方式调用ZygoteInit.main()，第一次进入Java世界；      
◐ registerZygoteSocket()建立socket通道，作为IPC通信服务端，zygote作为通信的服务端，用于响应客户端请求；   
◐ 执行 preloadClasses  和 preloadResource 函数，分别是加载class文件到内存，和加载资源文件到内存，这个过程是很耗时间的，所以开机会比较慢；  
◐ preload()预加载通用类、drawable和color资源、openGL以及共享库以及WebView，用于提高app启动效率；  
◐ 启动SystemServer进程。  

◆ 参考   
http://blog.csdn.net/qq_23547831/article/details/51104873  
http://blog.csdn.net/luoshengyang/article/details/6768304    
http://blog.csdn.net/itachi85/article/details/55047104  



