### Binder  
整个Android系统架构中，大量采用了Binder机制作为IPC方案，当然也存在部分其他的IPC方式，比如Zygote通信便是采用socket；  
RAM可以分为用户空间 和 内核空间，每一个进程只能运行在自己的工作空间，当然是在用户空间分配的，要想跨进程通信，只能通过内核空间；  
bindService、startActivity、sendBroadcast等操作都会用到Binder；  
Binder机制主要涉及到了四种角色：Client，Server，Service Manager， Binder driver。  
[BinderDriver](library/BinderDriver.md)  
[ServiceManager](library/ServiceManager.md)  
[Binder协议](library/BinderProtocol.md)  
[Binder实体](library/BinderEntity.md)  
[Binder引用](library/BinderReference.md)  
[Server注册到ServiceManager中](library/RegisterService.md)  
[Client获取远程服务](library/GetService.md)    
BinderProxy 继承自 Java 层的IBinder接口，  BpBinder 继承自 Native 层的接口；  
BpBinder 是由ProcessState创建的， BinderProxy是由javaObjectForIBinder函数通过JNI的NewObject() 创建的；  
BpBinder 是Native层的代理，又由javaObjectForIBinder函数转化成Java层的BinderProxy；  

Process 负责打开 Binder Device驱动设备，进行mmap等准备工作；  
IPCThreadState 负责Binder驱动的具体命令的通信；  
在getService()场景中，调用者从Java层的IBinder.transact()开始，层层往下调用到 IPCThreadState.transact()，  
然后通过waitForResponse进入主循环，直到ServiceManager恢复后，才结束，之后将结果回传给Java层；  


◆ 参考  
http://www.jianshu.com/p/4ee3fd07da14  
https://www.jianshu.com/p/4ee3fd07da14  
http://blog.csdn.net/universus/article/details/6211589###;  
https://github.com/AlanCheen/Android-Resources/blob/master/Binder.md  
http://weishu.me/2016/01/12/binder-index-for-newer/  
http://blog.csdn.net/luoshengyang/article/details/6618363  
http://blog.csdn.net/universus/article/details/6211589  
https://github.com/francistao/LearningNotes/blob/master/Part1/Android/Binder%E6%9C%BA%E5%88%B6.md  
http://hpw123.win/2017/01/04/Binder%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90/  
https://www.jianshu.com/p/1eff5a13000d  
http://blog.csdn.net/universus/article/details/6211589  




