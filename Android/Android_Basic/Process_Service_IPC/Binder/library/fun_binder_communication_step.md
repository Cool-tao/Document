##### Binder 跨进程通讯流程主要为如下 4 步：

###### ServiceManager 初始化   
当该应用程序启动时，ServiceManager 会和 Binder 驱动进行通信，告诉 Binder 驱动它是服务管理者  
Binder 驱动新建 ServiceManager 对应的 Binder 实体  

###### Server 向 ServiceManager 注册自己   
Server 向 Binder 驱动发起注册请求，Binder 为它创建 Binder 实体  
然后如果 ServiceManager 中没有这个 Server 时就添加 Server 名称与 Binder 引用到它的 Binder 引用表  

###### Client 获取远程服务   
Client 首先会向 Binder 驱动发起获取服务的请求，传递要获取的服务名称  
Binder 驱动将该请求转发给 ServiceManager 进程  
ServiceManager 查找到 Client 需要的 Server 对应的 Binder 实体的 Binder 引用信息，然后通过 Binder 驱动反馈给 Client  
Client 收到 Server 对应的 Binder 引用后，会创建一个 Server 对应的远程服务（即 Server 在当前进程的代理）  

###### Client 通过代理调用 Server   
Client 调用远程服务，远程服务收到 Client 请求之后，会和 Binder 驱动通信  
因为远程服务中有 Server 的 Binder 引用信息，因此驱动就能轻易的找到对应的 Server，进而将Client 的请求内容发送 Server  
