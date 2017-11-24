##### ThreadPoolExecutor

为什么要引入线程池呢？  
我们使用线程的时候就去创建一个线程，这样实现起来非常简便，但是就会有一个问题，如果并发的线程数量很多，  
并且每个线程都是执行一个时间很短的任务就结束了，这样频繁创建线程就会大大降低系统的效率，因为频繁创建线程和销毁线程需要时间。  

那么有没有一种办法使得线程可以复用，就是执行完一个任务，并不被销毁，而是可以继续执行其他的任务？  
在Java中可以通过线程池来达到这样的效果。  


[构造函数](TPE_Constructor.md)   
[重要的几个常量](TPE_Important_Constant_Fields.md)  
[重要的几个方法](TPE_Important_Method.md)  
[原理介绍](TPE_Principle.md)  


>  参考 


- http://www.importnew.com/19011.html


