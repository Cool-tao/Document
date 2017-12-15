##### Activity 生命周期 内存重启

①Activity从被装载到运行。则执行顺序为：onCreate() -> onStart()-> onResume();    
这是个典型过程，发生在Activity被系统装载运行时。    

②被清除出内存的Activity重新运行。执行顺序为：onCreate() -> onStart()-> onResume();    
这个过程对用户是透明的，用户并不会知道这个过程的发生，看起来如同①的执行顺序，    
不同的是如果保存有系统被清除出内出时的信息，会在调用onCreate()时，系统以参数的形式给出，而①中onCreate()的参数为null。  

