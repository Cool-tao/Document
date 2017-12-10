#### 传递参数  

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