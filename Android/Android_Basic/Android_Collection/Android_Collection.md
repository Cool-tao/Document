#### Collection  



[java_collection](../../../Java/java_source/java_collection/java_collection.md)  

[SimpleArrayMap](library/SimpleArrayMap.md)  
ArrayMap  
ArraySet  
SparseArray  
SparseBooleanArray  
SparseIntArray  
SparseLongArray  
LongSparseArray  

● 用法比较  
[用法比较](library/compare_using.md)、  

◆ ArrayMap、SparseArray与HashMap的比较  
● 安卓的集合框架，纯用数组实现；    
Java的HashMap，用数组、链表、红黑树实现；  
● 当出现hash冲突时，HashMap采用拉链法，ArrayMap采用线性探测法，明显HashMap的处理性能较优越；  
● 在数据量较小的安卓平台下，安卓的集合框架，更节省内存，时间复杂度是log2n，性能表现不俗；
● 如果是数据量较大，例如超过1K，还是用HashMap做，更加合理一些；  
● 同样也不是线程安全的；  


> 
