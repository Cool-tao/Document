###### Zygote 进程

[Android是基于Linux System的，当你的手机开机的时候，Linux的内核加载完成之后就会启动一个叫“init“的进程](fork_Zygote.md)，   
在Linux System里面，所有的进程都是由init进程fork出来的，zygote进程也不例外。  

所以当系统里面的第一个zygote进程运行之后，在这之后再开启App，就相当于开启一个新的进程。  
而为了实现资源共用和更快的启动速度，Android系统开启新进程的方式，是通过fork第一个zygote进程实现的。  
所以说，在Android系统中，除了第一个zygote进程，其他所有的应用程序进程以及系统服务进程SystemServer，  
都是由Zygote进程孕育（fork）出来的，都是zygote的子进程。  
  


> 参考 
- http://blog.csdn.net/qq_23547831/article/details/51104873
- http://blog.csdn.net/luoshengyang/article/details/6768304

