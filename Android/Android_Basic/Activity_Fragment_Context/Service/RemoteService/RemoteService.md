#### RemoteService  

● 什么是远程服务  
远程服务也被称作为 独立的进程（pid不一样），不受其他进程、主进程的影响；  

MainActivity  
```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState ?: Bundle())
    setContentView(R.layout.activity_main)
    LogTrack.w("进程id = " + Process.myPid())  // 进程id = 8328
}
```
LocalDownloadService  
```
@Override
public void onCreate() {
    super.onCreate();
    LogTrack.w("进程id = " + Process.myPid());  // 进程id = 8328
}
```
RemoteDownloadService  
```
@Override
public void onCreate() {
    super.onCreate();
    LogTrack.w("进程id = " + Process.myPid());  //  进程id = 8374
}
```
#### ● 优点
1.远程服务有自己的独立进程，不会受到其它进程的影响；  
2.可以被其它进程复用，提供公共服务；  
3.具有很高的灵活性。  

#### ● 缺点
相对普通服务，占用系统资源较多，使用AIDL进行IPC也相对麻烦。  
