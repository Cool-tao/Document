#### ThreadPoolExecutor 的重要常量

private final BlockingQueue<Runnable> workQueue;  //任务缓存队列，用来存放等待执行的任务  
private final ReentrantLock mainLock = new ReentrantLock();  //线程池的主要状态锁，对线程池状态（比如线程池大小  、runState等）的改变都要使用这个锁  
private final HashSet<Worker> workers = new HashSet<Worker>();  //用来存放工作集    
private volatile long  keepAliveTime;    //线程存货时间     
private volatile boolean allowCoreThreadTimeOut;   //是否允许为核心线程设置存活时间  
private volatile int   corePoolSize;     //核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）  
private volatile int   maximumPoolSize;   //线程池最大能容忍的线程数  
private volatile int   poolSize;       //线程池中当前的线程数  
private volatile RejectedExecutionHandler handler; //任务拒绝策略  
private volatile ThreadFactory threadFactory;   //线程工厂，用来创建线程  
private int largestPoolSize;   //用来记录线程池中曾经出现过的最大线程数   
private long completedTaskCount;   //用来记录已经执行完毕的任务个数  

##### 重点解释一下corePoolSize、maximumPoolSize、largestPoolSize三个变量      

corePoolSize在很多地方被翻译成核心池大小，其实我的理解这个就是线程池的大小。  

假如有一个工厂，工厂里面有10个工人，每个工人同时只能做一件任务。  
因此只要当10个工人中有工人是空闲的，来了任务就分配给空闲的工人做；  
当10个工人都有任务在做时，如果还来了任务，就把任务进行排队等待；  
如果说新任务数目增长的速度远远大于工人做任务的速度，那么此时工厂主管可能会想补救措施，比如重新招4个临时工人进来；  
然后就将任务也分配给这4个临时工人做；  
如果说着14个工人做任务的速度还是不够，此时工厂主管可能就要考虑不再接收新的任务或者抛弃前面的一些任务了。  
当这14个工人当中有人空闲时，而新任务增长的速度又比较缓慢，工厂主管可能就考虑辞掉4个临时工了，只保持原来的10个工人，  
毕竟请额外的工人是要花钱的。
这个例子中的corePoolSize就是10，而maximumPoolSize就是14（10+4）。  
也就是说corePoolSize就是线程池大小，maximumPoolSize在我看来是线程池的一种补救措施，即任务量突然过大时的一种补救措施。  
不过为了方便理解，在本文后面还是将corePoolSize翻译成核心池大小。  
largestPoolSize只是一个用来起记录作用的变量，用来记录线程池中曾经有过的最大线程数目，跟线程池的容量没有任何关系。  


