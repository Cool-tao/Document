#### ConcurrentHashMap  


◆ 锁分段技术  
ConcurrentHashMap由多个Segment组成(Segment下包含很多Node，也就是我们的键值对了)，每个Segment都有把锁来实现线程安全，  
当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。  

◆ 名词解释  
◑ CAS算法   
CAS(Compare And Swap)，  
unsafe.compareAndSwapInt(this, valueOffset, expect, update);   
意思是如果valueOffset位置包含的值与expect值相同，则更新valueOffset位置的值为update，并返回true，否则不更新，返回false。  


>  参考  

http://www.importnew.com/22007.html  
http://blog.csdn.net/u010723709/article/details/48007881  
http://blog.csdn.net/fjse51/article/details/55260493  
https://www.cnblogs.com/everSeeker/p/5601861.html  


