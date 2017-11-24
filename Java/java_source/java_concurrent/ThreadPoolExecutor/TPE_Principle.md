#### 线程池实现原理  

#### 1.线程池状态  

在ThreadPoolExecutor中定义了一个volatile变量，另外定义了几个static final变量表示线程池的各个状态：  
volatile int runState;  

static final int RUNNING    = 0;  
当创建线程池后，初始时，线程池处于RUNNING状态；  

static final int SHUTDOWN   = 1;  
如果调用了shutdown()方法，则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；  

static final int STOP       = 2;    
如果调用了shutdownNow()方法，则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；  


static final int TERMINATED = 3;    
当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。  


#### 2.任务的执行  

#### 3.线程池中的线程初始化  

#### 4.任务缓存队列及排队策略  

#### 5.任务拒绝策略  

#### 6.线程池的关闭  

#### 7.线程池容量的动态调整  