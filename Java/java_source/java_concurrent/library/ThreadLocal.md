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

◆ 参考  
https://my.oschina.net/xianggao/blog/392440?fromerr=CLZtT4xC
http://blog.csdn.net/lufeng20/article/details/24314381  
https://juejin.im/post/5a64a581f265da3e3b7aa02d  
