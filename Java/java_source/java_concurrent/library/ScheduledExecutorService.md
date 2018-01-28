### ScheduledExecutorService  
interface ScheduledExecutorService extends ExecutorService  

◆ schedule(callable, delay, timeUnit);  
```
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
ScheduledFuture<String> schedule0 = scheduledExecutorService.schedule(new LocalCallable(3000), 0, TimeUnit.MILLISECONDS);
ScheduledFuture<String> schedule1 = scheduledExecutorService.schedule(new LocalCallable(1000), 0, TimeUnit.MILLISECONDS);
LogTrack.w(schedule0.get());
LogTrack.w(schedule1.get());
scheduledExecutorService.shutdown();
```
虽然是并发执行，但是必须 schedule0结束，才能收到schedule1的结果；  
◆ scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);  
每次执行时间为： initialDelay、  initialDelay+period、  initialDelay+2*period、  ...  
是基于固定时间间隔进行任务调度；  
两次 轮询只间隔 period，不管上次有没有执行完；    
```
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new LocalRunnable(1500), 0, 3000, TimeUnit.MILLISECONDS);
```
◆ scheduleWithFixedDelay(runnable, initialDelay, delay, timeUnit);  
每次执行时间为： initialDelay、  initialDelay+executeTime+delay、  initialDelay+2*executeTime+2*delay、  ...  
取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度；  
先执行完上一次任务，再间隔period，再执行下一次轮询；  

