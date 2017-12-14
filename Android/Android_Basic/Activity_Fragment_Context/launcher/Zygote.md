##### Zygote 进程

[Android是基于Linux系统的，当手机开机的时候，Linux的内核加载完成之后就会启动一个“init“的进程](fork_Zygote.md)，   
在Linux系统中，所有的进程都是由init进程fork出来的，zygote进程也不例外。  
在Android系统中，所有的应用程序进程以及系统服务进程SystemServer，都是由Zygote进程fork出来的，都是zygote的子进程。  

◆ Zygote启动流程就讲到这，Zygote进程共做了如下几件事：   
◐ 创建DVM并为DVM注册JNI.   
◐ 通过JNI调用ZygoteInit的main函数进入Zygote的Java框架层。   
◐ 通过registerZygoteSocket函数创建服务端Socket，并通过runSelectLoop函数等待ActivityManagerService的请求来创建新的应用程序进程。   
◐ 启动SystemServer进程。  


> 参考   

http://blog.csdn.net/qq_23547831/article/details/51104873  
http://blog.csdn.net/luoshengyang/article/details/6768304    
http://blog.csdn.net/itachi85/article/details/55047104  



