#### ThreadPoolExecutor 几个重要的方法  

#### 关闭线程池  

shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务  
shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务  


#### Executors  

Executors.newCachedThreadPool();  //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE  
Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池  
Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池  

