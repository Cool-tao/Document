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

[用法比较](library/compare_using.md)、  

● 比较  
◆ ArrayMap、SparseArray与HashMap的比较  
● 安卓的集合框架，纯用数组实现；    
Java的HashMap，用数组、链表、红黑树实现；  
● 每次插入数据，  
ArrayMap的做法是，根据key生成的hash值，把hash值放在一个哈希表里面，用二分法算出插入的位置，  如果遇到hash冲突，会位移所有的hash值，保证其顺序性，也就是线性探测法；  
HashMap的做法是，根据key生成的hash值，与哈希表的length做位运算换取其在哈希表对应的index，如果遇到index冲突，记为哈希冲突，再用拉链法解决哈希冲突，  
当某个位置的链表过长，会把当前hash值对应的链表树化，就是转换成红黑树，当HashMap存入元素过多会对哈希表扩容，在这个查询与添加的算法上，hashMap明显有剩余ArrayMap，  
● 在数据量较小的安卓平台下，安卓的集合框架，更节省内存，时间复杂度是log2n，性能表现不俗；  
● 如果是数据量较大，例如超过1K，还是用HashMap做，更加合理一些；  
● 同样也不是线程安全的；  

