### CompletionService  
◆ 简单示例  
```
ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
CompletionService completionService = new ExecutorCompletionService(threadPoolExecutor);
LogTrack.w("计时开始");
for (int i = 0; i < 3; i++) {
    InnerCallable callable = new InnerCallable((i + 1) * 1000 + 1000); //  2  3  4
    completionService.submit(callable);
}
for (int i = 0; i < 3; i++) {
    Future future = completionService.take();
    LogTrack.w("hello  " + i);
    LogTrack.w("得到  " + future.get());
}
LogTrack.w("循环 外面");
threadPoolExecutor.shutdown();
```
我们知道简单的Future的缺点就是，各个Callable是串行运行的，是对CPU资源的浪费；  使用 CompletionService可以使多个Callable并行运行；  

