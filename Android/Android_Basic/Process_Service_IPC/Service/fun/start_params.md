#### 传递参数  

#### Intent传递参数  

●  context  
```
startService(serviceIntent.apply {
    putExtra("name", "Alex")
})
```

●  Service    
```
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
    LogTrack.i("onStartCommand");
    LogTrack.i(intent.getStringExtra("name"));
    return super.onStartCommand(intent, flags, startId);
}
```

#### bind传递参数    
原来，想要跟给service传递数据、从service中拿数据，就要用bind，远程service还要结合AIDL；  
这里说的给service传递数据，并不仅仅限制与在start的时候，用intent传递数据，还可以是在运行时的某个节点上，传递数据；    
