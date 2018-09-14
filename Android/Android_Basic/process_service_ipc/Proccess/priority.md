#### 进程优先级

● 前台进程(Foreground process)  
● 可见进程(Visible process)  
● 服务进程(Service process)  
● 后台进程(Background process)  
● 空进程(Empty process)  

◆ Foreground process  
用户当前操作所必需的进程，通常在任意给定时间前台进程都为数不多，只有在内存严重不足、无法运行时，系统才会终止它们，包括：  
拥有用户正在交互的 Activity，已调用onResume()  
拥有某个 Service，且绑定到用户正在交互的 Activity  
拥有正在“前台”运行的 Service，即服务已调用 startForeground()  
拥有正执行一个生命周期回调的 Service（onCreate()、onStart() 或 onDestroy()）  
拥有正执行其 onReceive() 方法的 BroadcastReceiver  

◆ Visible process  
没有任何前台组件、但仍会影响用户在屏幕上所见内容的进程。除非为了维持所有前台进程同时运行而必须终止，否则系统不会终止这些进程，包括：  
拥有不在前台、但仍对用户可见的 Activity，即已调用onPause()；  
拥有绑定到可见Activity 的 Service；  

◆ Service process  
尽管服务进程与用户所见内容没有直接关联，但是它们通常在执行一些用户关心的操作（例如，在后台播放音乐或从网络下载数据）。  
因此，除非内存不足以维持所有前台进程和可见进程同时运行，否则系统会让服务进程保持运行状态，包括：  
正在运行startService()方法启动的服务，且不属于上述两个更高类别进程的进程。  

◆ Background process  
后台进程对用户体验没有直接影响，系统可能随时终止它们，以回收内存供前台进程、可见进程或服务进程使用。  
通常会有很多后台进程在运行，因此它们会保存在LRU列表中，以确保包含用户最近查看的Activity的进程最后一个被终止。  
如果某个 Activity 正确实现了生命周期方法，并保存了其当前状态，则终止其进程不会对用户体验产生明显影响，  
因为当用户导航回该 Activity 时，Activity 会恢复其所有可见状态，例如：  
对用户不可见的Activity的进程（已调用Activity的onStop()方法）  

◆ Empty process  
保留这种进程的的唯一目的是用作缓存，以缩短下次在其中运行组件所需的启动时间。   
为使总体系统资源在进程缓存和底层内核缓存之间保持平衡，系统往往会终止这些进程，例如：  
不含任何活动应用组件的进程  

