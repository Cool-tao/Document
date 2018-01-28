### ThreadPoolExecutor

1. corePoolSize  
线程池所保存的线程数，包括空闲线程，也就是核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。  
在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，  
除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，是预创建线程的意思，  
即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，在创建了线程池后，线程池中的线程数为0，  
当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；

2. maximumPoolSize  
线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；

3. keepAliveTime  
表示线程没有任务执行时最多保持多久时间会终止。  
默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，  
即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。  
但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，  
直到线程池中的线程数为0；  

4. unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
TimeUnit.DAYS;               //天  
TimeUnit.HOURS;             //小时  
TimeUnit.MINUTES;           //分钟  
TimeUnit.SECONDS;           //秒  
TimeUnit.MILLISECONDS;      //毫秒  
TimeUnit.MICROSECONDS;      //微妙  
TimeUnit.NANOSECONDS;       //纳秒  

5. workQueue  
一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，  
一般来说，这里的阻塞队列有以下几种选择：
ArrayBlockingQueue;    基于数组的先进先出队列，此队列创建时必须指定大小；  
LinkedBlockingQueue;    基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；  
SynchronousQueue;    这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。  
PriorityBlockingQueue   
一般使用 LinkedBlockingQueue 和 SynchronousQueue ，线程池的排队策略与BlockingQueue有关。  

6. threadFactory  
线程工厂，主要用来创建线程；  

7. handler  
表示当拒绝处理任务时的策略，有以下四种取值：  
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。   
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。   
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）  
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务  

◆ 示例代码  
ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
