###  ThreadLocal  

当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。  

其实 ThreadLocal 并非是一个线程的本地实现版本，它并不是一个Thread，而是threadlocalvariable(线程局部变量)。也许把它命名为ThreadLocalVar更加合适。  
线程局部变量(ThreadLocal)其实的功能非常简单，就是为每一个使用该变量的线程都提供一个变量值的副本，是Java中一种较为特殊的线程绑定机制，  
是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。  

从线程的角度看，每个线程都保持对其线程局部变量副本的隐式引用，只要线程是活动的并且 ThreadLocal 实例是可访问的；  
在线程消失之后，其线程局部实例的所有副本都会被垃圾回收（除非存在对这些副本的其他引用）。  

ThreadLocal不是线程，是线程的一个变量，你可以先简单理解为线程类的属性变量。  
ThreadLocal 在类中通常定义为静态类变量。  
每个线程有自己的一个ThreadLocal，它是变量的一个‘拷贝’，修改它不影响其他线程。  

通过ThreadLocal.set()将这个新创建的对象的引用保存到各线程的自己的一个map中，每个线程都有这样一个map，执行ThreadLocal.get()时，各线程从自己的map中取出放进去的对象，  
因此取出来的是各自自己线程中的对象，ThreadLocal实例是作为map的key来使用的。 
如果ThreadLocal.set()进去的东西本来就是多个线程共享的同一个对象，那么多个线程的ThreadLocal.get()取得的还是这个共享对象本身，还是有并发访问问题。   


总之，ThreadLocal不是用来解决对象共享访问问题的，而主要是提供了保持对象的方法和避免参数传递的对象访问方式。归纳了两点：   
1。每个线程中都有一个自己的ThreadLocalMap类对象，可以将线程自己的对象保持到其中，各管各的，线程可以正确的访问到自己的对象。   
2。将一个共用的ThreadLocal静态实例作为key，将不同对象的引用保存到不同线程的ThreadLocalMap中，然后在线程执行的各处通过这个静态ThreadLocal实例的get()方法，  
取得自己线程保存的那个对象，避免了将这个对象作为参数传递的麻烦。   



◆ 参考  
https://my.oschina.net/xianggao/blog/392440?fromerr=CLZtT4xC
http://blog.csdn.net/lufeng20/article/details/24314381  
https://juejin.im/post/5a64a581f265da3e3b7aa02d  
https://juejin.im/post/5b5ecf9de51d45190a434308  
https://juejin.im/post/5a0e985df265da430e4ebb92  
