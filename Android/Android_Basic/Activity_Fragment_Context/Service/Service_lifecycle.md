#### Service生命周期  

启动一个服务 onCreate方法，只会被调用一次；  
onStartCommand方法执行的次数 等于 startService 被调用的次数；  

#### Service生命周期  
● 只使用 startService  
startService  →  onCreate  →   onStartCommand  →  【service running】  →  stopService  →  onDestroy    
● 只使用 bindService  
bindService  →  onCreate  →  onBind  →  【client are bound to service】  →  onUnbind  →  onDestroy  


