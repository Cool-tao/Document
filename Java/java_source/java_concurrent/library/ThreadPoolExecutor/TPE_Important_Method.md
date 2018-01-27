### ThreadPoolExecutor 几个重要的方法  

◆ beforeExecute 与 afterExecute  
```
ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

    }
};
```  
