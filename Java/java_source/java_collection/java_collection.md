###### java 集合框架

[Collection系列图](ImageFiles/Collection_001.png)、 [Map系列图](ImageFiles/Collection_002.png)  

> 简单描述 

- Map<K, V>  
Java中存储键值对的数据类型都实现了这个接口，表示“映射表”;  
支持的两个核心操作是get(Object key)以及put(K key, V value);  
分别用来获取键对应的值以及向映射表中插入键值对。

- Set<E>  
无序、不可重复的集合，  
它所支持的核心操作有add(E e),remove(Object o), contains(Object o);  
分别用于添加元素，删除元素以及判断给定元素是否存在于集中。

- List<E>  
有序、可以重复的集合；  


- Queue<E>  
Java集合框架中的队列接口，代表了“先进先出”队列。支持add(E element), remove()等操作。

- Stack<E>  
Java集合框架中表示堆栈的数据类型，堆栈是一种“后进先出”的数据结构。支持push(E item), pop()等操作。

> 用法比较
- [用法比较](Compare/Collection_MapTableSet_01.md)、
- [继承于实现的比较](Compare/Collection_MapTableSet_02.md)、

> 学习集合框架源码
[ArrayList](List_ArrayList.md)  
[LinkedList](List_LinkedList.md)    
[HashMap](Map_HashMap.md)  
[LinkedHashMap](Map_LinkedHashMap.md)
[TreeMap](Map_TreeMap.md)
LinkedTreeMap
LinkedHashTreeMap
ConcurrentHashMap
ConcurrentSkipListMap
IdentityHashMap
WeakHashMap
WeakClassHashMap
EnumMap

- Hashtable
- IdentityHashtable

- TreeSet
- //RegularEnumSet
- //JumboEnumSet
- HashSet
- LinkedHashSet
- CopyOnWriteArraySet
- ConcurrentSkipListSet
- BitSet

- Stack
- ArrayDeque
- PriorityQueue
- ConcurrentLinkedDeque

