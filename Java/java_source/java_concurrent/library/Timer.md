### Timer  

◆ 示例 
```
Timer timer = new Timer("timer", true);
TimerTask task = new TimerTask() {
    @Override
    public void run() {
        LogTrack.w("hello");
    }
};
LogTrack.w("schedule");
timer.schedule(task, 2000);
Thread.sleep(5000);
```

◑ 延时 delay 毫秒； 
timer.schedule(task, 2000);  
◑ 延时 delay 毫秒； 每隔 period 毫秒 开始下一次 task  
timer.schedule(task, 2000, 10);  

