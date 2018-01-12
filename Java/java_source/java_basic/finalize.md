###### finalize

请不要手动调用 finalize 方法，并不能达到回收资源效果，反而会影响性能；  


> finalize 方法的执行过程

当对象不可达时，GC会判断该对象是否重写了finalize()方法，如没有重写则直接将其回收，    
否则，若对象未执行过finalize()方法，将其放入F-Queue队列，由一低优先级线程执行该队列中对象的finalize()方法。    
执行finalize()方法完后，GC会再次判断该对象是否可达，若不可达则进行回收。否则对象“复活”。  



> finalize 方法的缺点

1. 不能保证finalize()方法会被执行；  
2. 不能保证finalize()方法会被及时的执行；  
3. 不能保证finalizer方法并被正确的执行，不同的JVM实现中finalizer的效果是不同的。  
4. 手动调用finalizer() 方法，耗时较长，比较影响性能；  





