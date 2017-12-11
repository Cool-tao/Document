#### 怎么绑定远程Service  

var downloadEntityAidl: IDownloadEntityAidlInterface? = null  
bindService(remoteServiceIntent, rdsConnection, Context.BIND_AUTO_CREATE)  

● 发消息  
```
downloadEntityAidl?.addMessage(DownloadMessageEntity(
        time = "2017-12-11  = ${Random().nextInt(100)}",
        content = "hello in Activity",
        title = "消息来了"))
        ?: ToastUtil.shortCenter("请先开启RemoteService")
```

● Connection  
```
private inner class RemoteDownloadServiceConnection : ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {
        //LogTrack.w(name)
    }

    override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
        downloadEntityAidl = IDownloadEntityAidlInterface.Stub.asInterface(iBinder)
    }
}

```