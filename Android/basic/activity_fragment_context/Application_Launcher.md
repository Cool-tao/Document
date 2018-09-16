### Launcher 启动流程

应用程序进程具有两个特点：  
一是进程的入口函数是ActivityThread.main，  
二是进程天然支持Binder进程间通信机制；  

◆ 简单描述    
[init 进程](launcher/fork_Zygote.md)  
[Zygote 进程](launcher/Zygote.md)  
[SystemServer 进程](launcher/SystemServer.md)  
[ActivityManagerService](launcher/ActivityManagerService.md)  
[LauncherActivity](launcher/LauncherActivity.md)  
[点击Launcher中，App的图标后，发生了什么](launcher/Launcher_Click_App_Icon.md)   
[startActivity](launcher/startActivity.md)  
[内存回收机制](/Application/trim/trim.md)  
ActivityThread  
App的真正入口，当开启App之后，会调用main()开始运行，开启消息循环队列，这就是传说中的UI线程或者叫主线程。与ActivityManagerServices配合，一起完成Activity的管理工作。  

ApplicationThread，用来实现ActivityManagerService与ActivityThread之间的交互。在ActivityManagerService需要管理相关Application中的Activity的生命周期时，  
通过ApplicationThread的代理对象与ActivityThread通讯。

ApplicationThreadProxy  
是ApplicationThread在服务器端的代理，负责和客户端的ApplicationThread通讯。AMS就是通过该代理与ActivityThread进行通信的。

[Instrumentation](launcher/Instrumentation.md)  

ActivityStack  
Activity在AMS的栈管理，用来记录已经启动的Activity的先后关系，状态信息等。通过ActivityStack决定是否需要启动新的进程。

ActivityRecord  
ActivityStack的管理对象，每个Activity在AMS对应一个ActivityRecord，来记录Activity的状态以及其他的管理信息。其实就是服务器端的Activity对象的映像。

TaskRecord  
AMS抽象出来的一个“任务”的概念，是记录ActivityRecord的栈，一个“Task”包含若干个ActivityRecord。AMS用TaskRecord确保Activity启动和退出的顺序。  

App 与 ActivityManagerService 通过 Binder 进行 IPC 通信，ActivityManagerService (SystemServer进程) 与 Zygote 通过 Socket 进行 IPC 通信。  
内核启动， fork Zygote 进程，Zygote 进程 fork SystemServer 进程， SystemServer fork ActivityManagerService 进程；  

init进程	Init.main()  
zygote进程	ZygoteInit.main()  
app_process进程	RuntimeInit.main()  
system_server进程	SystemServer.main()  
app进程	ActivityThread.main()  




◆ 参考   
Activity启动过程全解析  
http://blog.csdn.net/zhaokaiqiang1992/article/details/49428287  

Android应用程序进程启动过程的源代码分析  
http://blog.csdn.net/luoshengyang/article/details/6747696  

Android应用程序启动过程源代码分析  
http://blog.csdn.net/luoshengyang/article/details/6689748  

Android应用程序在新的进程中启动新的Activity的方法和过程分析  
http://blog.csdn.net/luoshengyang/article/details/6720261  

Android应用程序的Activity启动过程简要介绍和学习计划  
http://blog.csdn.net/luoshengyang/article/details/6685853  

Android进程间通信（IPC）机制Binder简要介绍和学习计划  
http://blog.csdn.net/luoshengyang/article/details/6618363  

Android系统启动流程（四）Launcher启动过程与系统启动流程  
http://blog.csdn.net/itachi85/article/details/56669808  
http://blog.csdn.net/itachi85/article/details/64123035  
http://blog.csdn.net/itachi85/article/details/64243223  
http://blog.csdn.net/itachi85/article/details/69388942  
http://blog.csdn.net/itachi85/article/details/72825768  
http://blog.csdn.net/itachi85/article/details/73065603  

Android深入四大组件（六）Android8.0 根Activity启动过程（前篇）    
http://blog.csdn.net/itachi85/article/details/78569299  

Android系统启动流程（四）Launcher启动过程与系统启动流程  
http://blog.csdn.net/itachi85/article/details/56669808  

http://www.jianshu.com/p/19e95bc40e37  
http://www.jianshu.com/p/f6234a975048  
http://blog.csdn.net/qq_23547831/article/details/51224992  

理解Android进程创建流程  
http://gityuan.com/2016/03/26/app-process-create/  

http://www.cnblogs.com/nokiaguy/archive/2013/04/14/3020774.html  
http://www.cnblogs.com/nokiaguy/p/3164799.html  
http://liuwangshu.cn/framework/booting/1-init.html  
https://blog.csdn.net/fu_kevin0606/article/details/53383031  
https://blog.csdn.net/sunao2002002/article/details/52454878  
https://blog.csdn.net/gaugamela/article/details/52133186  
https://blog.csdn.net/hu3167343/article/details/38230271  
http://liuwangshu.cn/framework/applicationprocess/1.html  
http://liuwangshu.cn/framework/applicationprocess/2.html  
http://liuwangshu.cn/framework/component/1-activity-start-1.html  
http://liuwangshu.cn/framework/component/1-activity-start-2.html  

