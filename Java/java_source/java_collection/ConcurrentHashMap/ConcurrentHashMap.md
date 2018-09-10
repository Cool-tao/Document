### ConcurrentHashMap    

ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成。  
Segment是一种可重入锁（ReentrantLock），在ConcurrentHashMap里扮演锁的角色；HashEntry则用于存储键值对数据。  
一个ConcurrentHashMap里包含一个Segment数组。Segment的结构和HashMap类似，是一种数组和链表结构。  
一个Segment里包含一个HashEntry数组，每个HashEntry是一个链表结构的元素，  
每个Segment守护着一个HashEntry数组里的元素，当对HashEntry数组的数据进行修改时，必须首先获得与它对应的Segment锁，  
◆ 锁分段技术  
ConcurrentHashMap由多个Segment组成(Segment下包含很多Node，也就是我们的键值对了)，每个Segment都有把锁来实现线程安全，  
当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。  

##### CAS  
在ConcurrentHashMap中，大量使用了U.compareAndSwapXXX的方法，这个方法是利用一个CAS算法实现无锁化的修改值的操作，他可以大大降低锁代理的性能消耗。   
这个算法的基本思想就是不断地去比较当前内存中的变量值与你指定的一个变量值是否相等，如果相等，则接受你指定的修改的值，否则拒绝你的操作。  
因为当前线程中的值已经不是最新的值，你的修改很可能会覆盖掉其他线程修改的结果。这一点与乐观锁，SVN的思想是比较类似的。  
unsafe代码块控制了一些属性的修改工作，比如最常用的SIZECTL 。 在这一版本的concurrentHashMap中，大量应用来的CAS方法进行变量、属性的修改工作。   
利用CAS进行无锁操作，可以大大提高性能。    
[几个常量的解释](library/constants.md)  
[扩容函数 transfer](library/fun_transfer.md)  

##### 名词解释  
◑ CAS算法   
CAS(Compare And Swap)，  
unsafe.compareAndSwapInt(this, valueOffset, expect, update);   
意思是如果valueOffset位置包含的值与expect值相同，则更新valueOffset位置的值为update，并返回true，否则不更新，返回false。  


◆ 参考  
http://www.importnew.com/22007.html  
http://blog.csdn.net/u010723709/article/details/48007881  
http://blog.csdn.net/fjse51/article/details/55260493  
https://www.cnblogs.com/everSeeker/p/5601861.html   
https://javadoop.com/post/hashmap  




