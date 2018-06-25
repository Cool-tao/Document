### Binder  
整个Android系统架构中，大量采用了Binder机制作为IPC方案，当然也存在部分其他的IPC方式，比如Zygote通信便是采用socket；  
RAM可以分为用户空间 和 内核空间，每一个进程只能运行在自己的工作空间，当然是在用户空间分配的，要想跨进程通信，只能通过内核空间；  
bindService、startActivity、sendBroadcast等操作都会用到Binder；  
Binder机制主要涉及到了四种角色：Client，Server，Service Manager， Binder driver。  
[BinderDriver](library/BinderDriver.md)  
[ServiceManager](library/ServiceManager.md)  
[Binder协议](library/BinderProtocol.md)  
[Binder实体  binder_node](library/BinderEntity.md)  
[Binder引用  binder_ref](library/BinderReference.md)  
[Server注册到ServiceManager中](library/RegisterService.md)  
[Client获取远程服务](library/GetService.md)    
[Binder通讯模型](library/BinderModel.md)   
[ProcessState](library/ProcessState.md)  
[IPCThreadState](library/IPCThreadState.md)  
[Binder 跨进程通讯流程主要为如下 4 步](library/fun_binder_communication_step.md)   
n 就是native        p 就是proxy  

java  
IBinder        BBinder        BpBinder        BpInterface        BnInterface        Binder        BinderProxy        Stub        Proxy        IInterface    

c++  
IBinder        BBinder        BpBinder        BpInterface        BnInterface        Binder        BpBuddy        BnBuddy        IInterface        RefBase        
BpRefBase        IPCThread        ProcessState        
[c++类图](ImageFiles/native_sevice_class_tree.png)   
Binder Driver  
binder_proc        binder_thread        binder_node        

BinderProxy 继承自 Java 层的IBinder接口，  BpBinder 继承自 Native 层的接口；  
BpBinder 是由ProcessState创建的， BinderProxy是由javaObjectForIBinder函数通过JNI的NewObject() 创建的；  
BpBinder 是Native层的代理，又由javaObjectForIBinder函数转化成Java层的BinderProxy；  

binder_proc是描述进程上下文信息的，每一个用户空间的进程都对应一个binder_proc结构体。  
binder_node是Binder实体对应的结构体，它是Server在Binder驱动中的体现。  
binder_ref是Binder引用对应的结构体，它是Client在Binder驱动中的体现。  

Process 负责打开 Binder Device驱动设备，进行mmap等准备工作；  
IPCThreadState 负责Binder驱动的具体命令的通信；  
在getService()场景中，调用者从Java层的IBinder.transact()开始，层层往下调用到 IPCThreadState.transact()，  
然后通过waitForResponse进入主循环，直到ServiceManager恢复后，才结束，之后将结果回传给Java层；  

AIDL：  
IInterface--Stub--Proxy--Stub具体实现  
ContentProvider：  
IContentProvider--ContentProviderNative--ContentProviderProxy--ContentProvider.Transport  
管理四大组件的AMS：  
IActivityManager--ActivityManagerNative--ActivityManagerProxy--ActivityManagerService  
负责ActivityThread和AMS之间的通讯  
IApplicationThread--ApplicationThreadNative--ApplicationThreadProxy--ApplicationThread  

IBinder代表跨进程传输的能力，  
IInterface则代表远程服务端具备的能力。    

Binder是IBinder的实现类，因此它具备跨进程传输的能力，它实际上就是远程Server端的Binder对象本身。  
Binder对象是Server端对象本身，是Server进程用的，与此对应的BinderProxy则是远程Binder的代理对象。  



◆ 参考  
https://blog.csdn.net/u011240877/article/details/72801425  
http://wangkuiwu.github.io/2014/09/01/Binder-Introduce/  
http://weishu.me/2016/01/12/binder-index-for-newer/  
https://www.jianshu.com/p/3d053abba04b  
https://blog.csdn.net/innost/article/details/47208049  
http://blog.csdn.net/luoshengyang/article/details/6618363  
http://blog.csdn.net/universus/article/details/6211589  
https://www.jianshu.com/p/1eff5a13000d  
https://blog.csdn.net/codefly/article/details/17058607
http://www.cnblogs.com/samchen2009/p/3316001.html  
https://blog.csdn.net/desler/article/details/47908017  
https://blog.csdn.net/coding_glacier/article/details/7520199  
https://blog.csdn.net/freekiteyu/article/details/70082302  
http://gityuan.com/2015/10/31/binder-prepare/  





