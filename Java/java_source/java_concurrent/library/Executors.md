### Executors  
Executors.newCachedThreadPool();  //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE  
Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池  
Executors.newScheduledThreadPool();  //创建一个定长线程池，支持定时及周期性任务执行。  
Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池  
