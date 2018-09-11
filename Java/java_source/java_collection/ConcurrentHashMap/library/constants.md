###  常亮解释  

####  sizeCtl含义  
private transient volatile int sizeCtl;  
1.负数代表正在进行初始化或扩容操作  
2.-1代表正在初始化    
3.-N 表示有N-1个线程正在进行扩容操作  
4.正数或0代表hash表还没有被初始化，这个数值表示初始化或下一次进行扩容的大小，这一点类似于扩容阈值的概念。  
还后面可以看到，它的值始终是当前ConcurrentHashMap容量的0.75倍，这与loadfactor是对应的。  

