#### Service生命周期  

#### 启动一个服务  
启动一个服务有两种方式，start与bind；  
如果需要在运行时，context和service传递参数，要用bind方式，如果是一个远程服务，还需要结合AIDL；  
如果只需要在启动时，给service差U年底参数，start和bind都是可以的；
start方式，调用简单，方便控制。无法对服务内部状态进行操控，缺乏灵活性；    
bind方式，调用稍难，运用灵活，可以通过 IBinder 接口中获取 Service 的句柄，对 Service 状态进行检测。  
从 Android 系统设计的架构上看，startService() 是用于启动本地服务，bindService() 更多是用于对远程服务进行绑定。  
两者可以混合使用；  

#### start  
启动一个服务 onCreate方法，只会被调用一次；  
onStartCommand方法执行的次数 等于 startService 被调用的次数； 

#### 隐式意图  
如果启动service的intent的component和package都为空并且版本大于LOLLIPOP(5.0)的时候,直接抛出异常；  
调用setPackage，解决问题；  
```
private void startService(String serviceAction) {
    Context context = BaseUtil.context();
    Intent intent = new Intent();
    intent.setAction(serviceAction);
    intent.setPackage(context.getPackageName());
    context.startService(intent);
}
```

#### Service生命周期  
● 只使用 startService  
startService  →  onCreate  →   onStartCommand  →  【service running】  →  stopService  →  onDestroy    
● 只使用 bindService  
bindService  →  onCreate  →  onBind  →  【client are bound to service】  →  onUnbind  →  onDestroy  

> 关于启动服务与绑定服务间的转换问题  

不管是启动服务还是绑定服务，操作的是同一个Service实例；  

● 先绑定服务后启动服务  
如果当前Service实例先以绑定状态运行，然后再以启动状态运行，那么绑定服务将会转为启动服务运行，  
这时如果之前绑定的宿主（Activity）被销毁了，也不会影响服务的运行，服务还是会一直运行下去，直到收到调用停止服务或者内存不足时才会销毁该服务。  
● 先启动服务后绑定服务    
如果当前Service实例先以启动状态运行，然后再以绑定状态运行，当前启动服务并不会转为绑定服务，  
但是还是会与宿主绑定，只是即使宿主解除绑定后，服务依然按启动服务的生命周期在后台运行，  
直到有Context调用了stopService()或是服务本身调用了stopSelf()方法抑或内存不足时才会销毁服务。  

