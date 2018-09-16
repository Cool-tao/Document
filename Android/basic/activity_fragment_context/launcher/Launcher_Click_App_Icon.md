### 点击Launcher中，App的图标后，发生了什么  

◑ 点击桌面App图标，Launcher进程采用Binder IPC向system_server进程发起startActivity请求；  
◑ system_server进程接收到请求后，向zygote进程发送创建进程的请求；  
◑ Zygote进程fork出新的子进程，即App进程；  
◑ App进程，通过Binder IPC向sytem_server进程发起attachApplication请求；  
◑ system_server进程在收到请求后，进行一系列准备工作后，再通过binder IPC向App进程发送scheduleLaunchActivity请求；  
◑ App进程的binder线程（ApplicationThread）在收到请求后，通过handler向主线程发送LAUNCH_ACTIVITY消息；  
◑ 主线程在收到Message后，通过发射机制创建目标Activity，并回调Activity.onCreate()等方法。  
◑ 到此，App便正式启动，开始进入Activity生命周期，执行完onCreate/onStart/onResume方法，UI渲染结束后便可以看到App的主界面。  


◆ 参考  
https://github.com/LRH1993/android_interview/blob/master/android/advance/app-launch.md  
https://github.com/yipianfengye/androidSource/blob/master/14%20activity%E5%90%AF%E5%8A%A8%E6%B5%81%E7%A8%8B.md  
http://gityuan.com/2016/03/12/start-activity/    

