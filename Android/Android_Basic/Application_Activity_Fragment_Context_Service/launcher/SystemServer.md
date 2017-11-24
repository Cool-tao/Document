###### SystemServer 进程

SystemServer一个进程，是由zygote进程fork出来的。系统里面重要的服务都是在这个进程里面开启的，比如   
ActivityManagerService、PackageManagerService、WindowManagerService等等。  

是在 ZygoteInit.main()方法里 调用 startSystemServer 方法 fork 了 SystemServer；  


> 参考
- http://blog.csdn.net/qq_23547831/article/details/51105171
