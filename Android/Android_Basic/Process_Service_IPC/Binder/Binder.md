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


链接  
BnInterface    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/IInterface.h#63  
BpInterface    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/IInterface.h#63    

IBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/Binder.h  
IBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/Binder.h  
BpBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/BpBinder.h  
BpBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/BpBinder.h  
BBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/Binder.h#27  
BBinder    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/Binder.h#27  
RefBase    http://androidxref.com/8.0.0_r4/xref/frameworks/rs/cpp/util/RefBase.h#65  
IServiceManager    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/IServiceManager.h  
IServiceManager    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/IServiceManager.h    
BpRefBase    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/include/binder/Binder.h#80  
BpRefBase    http://androidxref.com/8.0.0_r4/xref/frameworks/native/include/binder/Binder.h#80   
BpServiceManager    http://androidxref.com/8.0.0_r4/xref/frameworks/native/libs/binder/IServiceManager.cpp#127    

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
https://blog.csdn.net/luoshengyang/article/details/6642463  
http://blog.csdn.net/universus/article/details/6211589  
https://www.jianshu.com/p/1eff5a13000d  
https://blog.csdn.net/codefly/article/details/17058607
http://www.cnblogs.com/samchen2009/p/3316001.html  
https://blog.csdn.net/desler/article/details/47908017  
https://blog.csdn.net/coding_glacier/article/details/7520199  
https://blog.csdn.net/freekiteyu/article/details/70082302  
http://gityuan.com/2015/10/31/binder-prepare/  
https://blog.csdn.net/carson_ho/article/details/73560642  
https://www.jianshu.com/p/f4f722d3e51d  
https://mr-cao.gitbooks.io/android/content/android-binder.html   
https://cloud.tencent.com/developer/article/1056116  
http://sniffer.site/2016/10/01/Binder%E5%85%A5%E9%97%A8/  
https://70kg.info/2016/07/09/Android%20Binder%20%E6%B5%85%E6%9E%90/  
http://mouxuejie.com/blog/2018-05-12/learning-notes-binder/  
http://blog.imallen.wang/archives/page/6/  
https://blog.csdn.net/bettarwang/article/details/51166823  
https://juejin.im/entry/59c9cd8e518825745c636ffd  
https://github.com/android-cjj/SourceAnalysis-1/blob/master/Binder%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.md  
https://www.cnblogs.com/a284628487/p/3187320.html  
https://www.diycode.cc/topics/384  
https://www.jianshu.com/p/88fd0dcd0528  
https://adbcode.com/2017/05/11/Android%20Binder%E5%88%86%E6%9E%90/  
https://adbcode.com/2017/06/27/Android%20Binder%E8%BF%9B%E9%98%B6/  
https://adbcode.com/2017/06/27/%E8%AF%A6%E8%A7%A3Binder%E7%B1%BB/  
http://blog.51cto.com/13064681/1944339  
http://windrunnerlihuan.com/2016/06/12/Binder%E7%AE%80%E8%A6%81%E5%88%86%E6%9E%90/  
https://www.kancloud.cn/alex_wsc/androidsystem/483924  
https://www.ctolib.com/topics-79743.html  
http://houzhi.me/2016/04/30/android-sourcecode-binder-process-analysis/  
http://www.cnblogs.com/innost/archive/2011/01/09/1931456.html  
https://blog.csdn.net/u010132993/article/details/72582655  








