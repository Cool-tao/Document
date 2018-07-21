### 并发编程   
当多个线程访问一个类时，如果不用考虑这些线程在运行时环境下的调度和交替运行，并且不需要额外的同步及在调用方代码不必做其他的协调，  
这个类的行为仍然是正确的，那么这个类就是线程安全的。  
[多线程架构图](ImageFiles/MT_001.png)  
#### 示例代码  
[示例-周期执行](sample/ses_01.md)   
[示例-并发执行，多任务完成，反馈](sample/ses_01.md)   
#### 类逐个介绍  
[Synchronized](library/synchronized.md)  
[Executors](library/Executors.md)  
[ExecutorService](library/ExecutorService.md)  
[ScheduledExecutorService](library/ScheduledExecutorService.md)  
[ThreadPoolExecutor](library/ThreadPoolExecutor.md)  
[ScheduledThreadPoolExecutor](library/ScheduledThreadPoolExecutor.md)  
[Future](library/Future.md)  
[CompletionService](library/CompletionService.md)  
[Timer](library/Timer.md)  
[Semaphore](library/Semaphore.md)  
[ReentrantLock](library/ReentrantLock.md)  
[Exchanger](library/Exchanger.md)  
[Atomic](library/Atomic)  
ArrayBlockingQueue  
AtomicBoolean  
AtomicLong  
AtomicReference  
AtomicStampedReference  
AtomicIntegerArray  
AtomicLongArray  
AtomicReferenceArray  
BlockingQueue  
BlockingDeque  
CountDownLatch  
ConcurrentMap  
ConcurrentNavigableMap  
CyclicBarrier  
DelayQueue  
Executor
ForkJoinPool  
LinkedBlockingQueue  
LinkedBlockingDeque  
Lock  
Phaser  
PriorityBlockingQueue  
ReadWriteLock  
SynchronousQueue  

◆ 参考  
http://tutorials.jenkov.com/java-util-concurrent/index.html  
http://www.importnew.com/26678.html  
https://juejin.im/post/5a5de7a86fb9a01caf376cc1  
https://www.ibm.com/developerworks/cn/java/j-lo-taskschedule/  
http://www.blogjava.net/xylz/category/45607.html  
http://www.blogjava.net/xylz/archive/2010/07/08/325587.html  
http://wangkuiwu.github.io/2012/08/15/juc-executor01/  
https://blog.csdn.net/tianshi_kco/article/details/52960743  

