### 并发编程   
当多个线程访问一个类时，如果不用考虑这些线程在运行时环境下的调度和交替运行，并且不需要额外的同步及在调用方代码不必做其他的协调，  
这个类的行为仍然是正确的，那么这个类就是线程安全的。  
[多线程架构图](ImageFiles/MT_001.png)  
#### 示例代码  
[线程安全与线程不安全](sample/safe_unsafe.md)   
[简化封装 ThreadUtil](library/ThreadUtil.md)  
[示例-周期执行](sample/ses_01.md)   
[示例-并发执行，多任务完成，反馈](sample/es_01.md)   
[示例-并发执行，多任务完成，反馈 ExecutorCompletionService](sample/es_01.md)   
#### 类逐个介绍  
[Unsafe CAS 比较并交换](library/Unsafe.md)  
[Synchronized](library/synchronized.md)  
[ThreadLocal](library/ThreadLocal.md)   
[Executors](library/Executors.md)  
[ExecutorService](library/ExecutorService.md)  
[ScheduledExecutorService](library/ScheduledExecutorService.md)  
[ThreadPoolExecutor](ThreadPoolExecutor/ThreadPoolExecutor.md)  
[ScheduledThreadPoolExecutor](library/ScheduledThreadPoolExecutor.md)  
[Future](library/Future.md)  
[CompletionService](library/CompletionService.md)  
[Timer](library/Timer.md)  
[Semaphore](library/Semaphore.md)  
[ReentrantLock](library/ReentrantLock.md)  
[Exchanger](library/Exchanger.md)  
[Atomic](library/Atomic)  
[CountDownLatch](library/CountDownLatch.md)  
[CyclicBarrier](library/CyclicBarrier.md)  
[LockSupport](library/LockSupport.md)  
Executor ;
ThreadLocalRandom  
LinkedBlockingQueue ;
LinkedBlockingDeque ;
Lock ;
Phaser ;
PriorityBlockingQueue ;
ReadWriteLock ;
SynchronousQueue ;
RecursiveAction ;
RecursiveTask ;
ForkJoinPool ;
ForkJoinTask ;
ForkJoinWorkerThread ;
CompletableFuture ;
CompletionStage ;
CountedCompleter ;
FutureTask ;
RunnableFuture ;
RunnableScheduledFuture ;
ScheduledFuture ;
◆ 参考  
http://tutorials.jenkov.com/java-util-concurrent/index.html  
http://www.importnew.com/26678.html  
https://juejin.im/post/5a5de7a86fb9a01caf376cc1  
https://www.ibm.com/developerworks/cn/java/j-lo-taskschedule/  
http://www.blogjava.net/xylz/category/45607.html  
http://www.blogjava.net/xylz/archive/2010/07/08/325587.html  
http://wangkuiwu.github.io/2012/08/15/juc-executor01/  
https://blog.csdn.net/tianshi_kco/article/details/52960743  
http://cmsblogs.com/?cat=97  
http://www.cnblogs.com/paddix/p/5374810.html  
