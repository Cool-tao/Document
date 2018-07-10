### java 集合框架

[Collection系列图](ImageFiles/Collection_001.png)、 [Map系列图](ImageFiles/Collection_002.png)  

◆ 简单描述 
◑ Map<K, V>    
Java中存储键值对的数据类型都实现了这个接口，表示“映射表”;  
支持的两个核心操作是get(Object key)以及put(K key, V value);  
分别用来获取键对应的值以及向映射表中插入键值对。
◑ Set<E>  
无序、不可重复的集合；查询效率低，删除和插入效率高；     
◑ List<E>  
有序、可以重复的集合；查询效率高，删除和插入效率低；      
◑ Queue<E>  
Java集合框架中的队列接口，代表了“先进先出”队列。支持add(E element), remove()等操作。
◑ Stack<E>  
Java集合框架中表示堆栈的数据类型，堆栈是一种“后进先出”的数据结构。支持push(E item), pop()等操作。

◆ 简单比较  
[用法比较](Compare/Collection_MapTableSet_01.md)、  
[继承于实现的比较](Compare/Collection_MapTableSet_02.md)、  

◆ 学习集合框架源码  
[ArrayList](Library/List_ArrayList.md)    
[LinkedList](Library/List_LinkedList.md)      
[HashMap](HashMap/HashMap.md)    
[LinkedHashMap](LinkedHashMap/LinkedHashMap.md)    
[TreeMap](Library/Map_TreeMap.md)    
LinkedTreeMap  
LinkedHashTreeMap  
[ConcurrentHashMap](ConcurrentHashMap/ConcurrentHashMap.md)  
ConcurrentSkipListMap  
IdentityHashMap  
WeakHashMap  
WeakClassHashMap  
EnumMap  

Hashtable  
IdentityHashtable  

TreeSet  
//RegularEnumSet  
//JumboEnumSet  
HashSet  
LinkedHashSet  
◑ CopyOnWriteArrayList  
很适合处理处理，读取频繁，但很少有写操作；  
CopyOnWriteArraySet  
ConcurrentSkipListSet  
BitSet  

Stack  
ArrayDeque  
PriorityQueue  
ConcurrentLinkedDeque  

◆ 参考  
https://m.th7.cn/show/4/201706/1185867.html#  
http://blog.csdn.net/seu_calvin/article/details/52653711  
http://blog.csdn.net/zhangerqing/article/details/8193118  
http://www.cnblogs.com/beatIteWeNerverGiveUp/p/5709841.html  
http://blog.csdn.net/paincupid/article/details/47746341  
https://juejin.im/post/593e5364ac502e006c0c7690  
http://blog.csdn.net/tsyj810883979/article/details/6891540  
http://blog.csdn.net/tsyj810883979/article/details/6892575  
http://blog.csdn.net/tsyj810883979/article/details/6897043  

