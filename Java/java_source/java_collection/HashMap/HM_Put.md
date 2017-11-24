##### Put 操作

> 结论

如果在创建HashMap实例时没有给定capacity、loadFactor则默认值分别是16和0.75。 
当好多bin被映射到同一个桶时，如果这个桶中bin的数量小于TREEIFY_THRESHOLD当然不会转化成树形结构存储；  
如果这个桶中bin的数量大于了 TREEIFY_THRESHOLD ，但是capacity小于MIN_TREEIFY_CAPACITY 则依然使用链表结构进行存储，此时会对HashMap进行扩容；  
如果capacity大于了MIN_TREEIFY_CAPACITY ，则会进行树化。  

> put操作的过程

put方法分两种情况，bucket是以链表形式存储的还是以树形结构存储的。  
如果是key已存在则修改旧值，并返回旧值，  
如果key不存在，则执行插入操作，返回null。  
如果是插入操作还要modCount++。  
如果是链表存储时，如果插入元素之后超过了TREEIFY_THRESHOLD，还要进行树化操作。   
put操作，当发生碰撞时，如果是使用链表处理冲突，执行的尾插法。  
这个跟ConcurrentHashMap不同，ConcurrentHashMap执行的是头插法。因为，其HashEntry的next是final的。   
put操作的基本流程：   
（1）通过hash值得到所在bucket的下标，如果为null，表示没有发生碰撞，则直接put   
（2）如果发生了碰撞，则解决发生碰撞的实现方式：链表还是树。   
（3）如果能够找到该key的结点，则执行更新操作，无需对modCount增1。   
（4）如果没有找到该key的结点，则执行插入操作，需要对modCount增1。   
（5）在执行插入操作时，如果bucket中bin的数量超过TREEIFY_THRESHOLD，则要树化。   
（6）在执行插入操作之后，如果数组size超过了threshold，这要扩容。  

