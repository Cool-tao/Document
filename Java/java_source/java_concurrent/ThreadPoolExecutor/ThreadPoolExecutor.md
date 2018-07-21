### ThreadPoolExecutor
ThreadPoolExecutor extends AbstractExecutorService  
AbstractExecutorService implements ExecutorService  
为什么要引入线程池呢？  
我们使用线程的时候就去创建一个线程，这样实现起来非常简便，但是就会有一个问题，如果并发的线程数量很多，  
并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。  

那么有没有一种办法使得线程可以复用，就是执行完一个任务，并不被销毁，而是可以继续执行其他的任务？  
在Java中可以通过线程池来达到这样的效果。  

[构造函数](TPE_Constructor.md)   
[重要的几个常量](TPE_Important_Constant_Fields.md)  
[重要的几个方法](TPE_Important_Method.md)  
[原理介绍](TPE_Principle.md)  

##### API  
```
// 基于完成执行给定 Runnable 所调用的方法。
protected void afterExecute(Runnable r, Throwable t)
// 如果在保持活动时间内没有任务到达，新任务到达时正在替换（如果需要），则设置控制核心线程是超时还是终止的策略。
void allowCoreThreadTimeOut(boolean value)
// 如果此池允许核心线程超时和终止，如果在 keepAlive 时间内没有任务到达，新任务到达时正在替换（如果需要），则返回 true。
boolean allowsCoreThreadTimeOut()
// 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。
boolean awaitTermination(long timeout, TimeUnit unit)
// 在执行给定线程中的给定 Runnable 之前调用的方法。
protected void beforeExecute(Thread t, Runnable r)
// 在将来某个时间执行给定任务。
void execute(Runnable command)
// 当不再引用此执行程序时，调用 shutdown。
protected void finalize()
// 返回主动执行任务的近似线程数。
int getActiveCount()
// 返回已完成执行的近似任务总数。
long getCompletedTaskCount()
// 返回核心线程数。
int getCorePoolSize()
// 返回线程保持活动的时间，该时间就是超过核心池大小的线程可以在终止前保持空闲的时间值。
long getKeepAliveTime(TimeUnit unit)
// 返回曾经同时位于池中的最大线程数。
int getLargestPoolSize()
// 返回允许的最大线程数。
int getMaximumPoolSize()
// 返回池中的当前线程数。
int getPoolSize()
// 返回此执行程序使用的任务队列。
BlockingQueue<Runnable> getQueue()
// 返回用于未执行任务的当前处理程序。
RejectedExecutionHandler getRejectedExecutionHandler()
// 返回曾计划执行的近似任务总数。
long getTaskCount()
// 返回用于创建新线程的线程工厂。
ThreadFactory getThreadFactory()
// 如果此执行程序已关闭，则返回 true。
boolean isShutdown()
// 如果关闭后所有任务都已完成，则返回 true。
boolean isTerminated()
// 如果此执行程序处于在 shutdown 或 shutdownNow 之后正在终止但尚未完全终止的过程中，则返回 true。
boolean isTerminating()
// 启动所有核心线程，使其处于等待工作的空闲状态。
int prestartAllCoreThreads()
// 启动核心线程，使其处于等待工作的空闲状态。
boolean prestartCoreThread()
// 尝试从工作队列移除所有已取消的 Future 任务。
void purge()
// 从执行程序的内部队列中移除此任务（如果存在），从而如果尚未开始，则其不再运行。
boolean remove(Runnable task)
// 设置核心线程数。
void setCorePoolSize(int corePoolSize)
// 设置线程在终止前可以保持空闲的时间限制。
void setKeepAliveTime(long time, TimeUnit unit)
// 设置允许的最大线程数。
void setMaximumPoolSize(int maximumPoolSize)
// 设置用于未执行任务的新处理程序。
void setRejectedExecutionHandler(RejectedExecutionHandler handler)
// 设置用于创建新线程的线程工厂。
void setThreadFactory(ThreadFactory threadFactory)
// 按过去执行已提交任务的顺序发起一个有序的关闭，但是不接受新任务。
void shutdown()
// 尝试停止所有的活动执行任务、暂停等待任务的处理，并返回等待执行的任务列表。
List<Runnable> shutdownNow()
// 当 Executor 已经终止时调用的方法。
protected void terminated()
```

◆ 参考  
http://www.importnew.com/19011.html  
https://blog.csdn.net/tianshi_kco/article/details/53026179    
https://blog.csdn.net/qq_25806863/article/details/71126867  
https://www.cnblogs.com/trust-freedom/p/6594270.html  
http://www.cnblogs.com/trust-freedom/p/6681948.html  
http://www.cnblogs.com/trust-freedom/p/6693601.html  


