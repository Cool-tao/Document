### Android进程间通信（IPC）机制Binder  
传统的IPC机制，只能适用于父子、兄弟之间的亲属关系的进程之间通信，有：
管道（Pipe）、信号（Signal）、跟踪（Trace）；  
后来新增：命名管道（Named Pipe）、报文队列（Message）、共享内存（Share Memory）、信号量（Semaphore）、套接字（Socket）  
◆ 性能考虑  
◑ socket  
socket作为一个通用接口，传输效率低、开销大，主要用在跨网络的进程间通信和本机上的低速通信；  
◑ 消息队列和管道  
消息队列和管道通信，采用存储 - 转发方式，即数据先从发送方缓存区拷贝到内核开辟的缓存区中，然后再从内核缓存区拷贝到接收方缓存区，至少有两次拷贝过程；   
而采用Binder机制的话，则只需要经过1次内存拷贝即可！ 即，从发送方的缓存区拷贝到内核的缓存区，而接收方的缓存区与内核的缓存区是映射到同一块物理地址的；  
◑ 共享内存  
共享内存虽然无需拷贝，但控制复杂，难以使用；  
◆ 安全考虑 
传统IPC没有任何安全措施，完全依赖上层协议；  
传统IPC的接收方无法获得对方进程可靠的UID/PID（用户ID/进程ID），从而无法鉴别对方身份；  
Android为每个安装好的应用程序分配了自己的UID，故进程的UID是鉴别进程身份的重要标志；  
◆ 结论  
基于以上原因，Android需要建立一套新的IPC机制来满足系统对通信方式，传输性能和安全性的要求，采用基于OpenBinder实现的Binder通信机制；  
Binder基于Client-Server通信模式，传输过程只需一次拷贝，为发送发添加UID/PID身份，既支持实名Binder也支持匿名Binder，安全性高；  

[Binder](Binder/Binder.md)    

◆ 参考  
http://blog.csdn.net/luoshengyang/article/details/6618363  
http://www.cnblogs.com/innost/archive/2011/01/09/1931456.html  
http://gityuan.com/2015/10/31/binder-prepare/  
http://blog.csdn.net/universus/article/details/6211589  
http://wangkuiwu.github.io/2014/09/01/Binder-Introduce/  
