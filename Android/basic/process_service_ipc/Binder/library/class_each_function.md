#### 各个类的作用  
ServiceManager：通过getIServiceManager方法获取的是ServiceManagerProxy对象；   
ServiceManager的addService, getService实际工作都交由ServiceManagerProxy的相应方法来处理；  

ServiceManagerProxy：其成员变量mRemote指向BinderProxy对象，ServiceManagerProxy的addService, getService方法最终是交由mRemote来完成。  
ServiceManagerNative：其方法asInterface()返回的是ServiceManagerProxy对象，ServiceManager便是借助ServiceManagerNative类来找到ServiceManagerProxy；  
Binder：其成员变量mObject和方法execTransact()用于native方法  
BinderInternal：内部有一个GcWatcher类，用于处理和调试与Binder相关的垃圾回收。  
IBinder：接口中常量FLAG_ONEWAY：客户端利用binder跟服务端通信是阻塞式的，但如果设置了FLAG_ONEWAY，  
这成为非阻塞的调用方式，客户端能立即返回，  服务端采用回调方式来通知客户端完成情况。另外IBinder接口有一个内部接口DeathDecipient(死亡通告)。  

[类图](../ImageFiles/java_binder_framework.jpg)   
