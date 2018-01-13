### Android进程间通信（IPC）机制Binder  
传统的IPC机制，只能适用于父子、兄弟之间的亲属关系的进程之间通信，有：
管道（Pipe）、信号（Signal）、跟踪（Trace）；  
后来新增：命名管道（Named Pipe）、报文队列（Message）、共享内存（Share Memory）、信号量（Semaphore）  
Android并，没有采用以上通信机制，而是基于OpenBinder实现的Binder通信机制；  
整个Android系统架构中，大量采用了Binder机制作为IPC（进程间通信）方案，当然也存在部分其他的IPC方式，比如Zygote通信便是采用socket。  
RAM可以分为用户空间 和 内核空间，每一个进程只能运行在自己的工作空间，当然是在用户空间分配的，要想跨进程通信，只能通过内核空间；  
ioctl是设备驱动程序中对设备的I/O通道进行管理的函数  
[Binder](Binder/Binder.md)    

◆ 参考  
http://blog.csdn.net/luoshengyang/article/details/6618363  
http://www.cnblogs.com/innost/archive/2011/01/09/1931456.html  
http://gityuan.com/2015/10/31/binder-prepare/  



