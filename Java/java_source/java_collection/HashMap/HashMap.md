###### HashMap

> 简单描述
- HashMap 基于 数组、链表、红黑树；
- HashMap 同时允许 key、value 都可以为空；
- HashMap 不是 线程安全的，可以用synchronize 实现线程安全，在高并发下，应该用 ConcurrentHashMap，建议不要再使用 Hashtable了；
- HashMap 的默认容量是16，

> HashMap 的实现

HashMap的实现使用了数组、链表、红黑树；  
数组的每个元素，称之为箱子，箱子里存放桶；  
桶的数据结构有2种可能：链表、红黑树；  
HashMap使用key的hashCode来寻找存储位置；  
不同的key可能具有相同的hashCode，这时候就出现哈希冲突了，也叫做哈希碰撞，为了解决哈希冲突，  
常见办法有开放地址方法、链地址方法，HashMap的实现上选取了链地址方法；  
也就是将哈希值一样的，但是equals是false的，entry保存在同一个数组项里面，  
每一个数组项当做一个桶，桶里面装的entry的key的hashCode是一样的；

- [几个常量的解释](HM_Constants.md)
- [Put 操作](HM_Put.md)



> 参考 

- HashMap类的注释翻译  
http://blog.csdn.net/fan2012huan/article/details/510859243.

- HashMap中capacity、loadFactor、threshold、size等概念的解释  
http://blog.csdn.net/fan2012huan/article/details/51087722

- HashMap的扩容及树化过程  
http://blog.csdn.net/fan2012huan/article/details/51088211

- HashMap源码注解 之 常量定义（一）  
http://blog.csdn.net/fan2012huan/article/details/51094454

- HashMap源码注解 之 成员变量（二）  
http://blog.csdn.net/fan2012huan/article/details/51094525

- HashMap源码注解 之 内部数据结构 Node （三）  
http://blog.csdn.net/fan2012huan/article/details/51097264

- HashMap源码注解 之 静态工具方法hash()、tableSizeFor()（四）  
http://blog.csdn.net/fan2012huan/article/details/51097331

- HashMap源码注解 之 get()方法（五）  
http://blog.csdn.net/fan2012huan/article/details/51130576

- HashMap源码注解 之 put()方法（六）  
http://blog.csdn.net/fan2012huan/article/details/51233378

- HashMap源码注解 之 resize()方法（七）  
http://blog.csdn.net/fan2012huan/article/details/51233540

- http://www.jianshu.com/p/aa017a3ddc40
- http://www.jianshu.com/p/e2f75c8cce01
- http://blog.csdn.net/lianhuazy167/article/details/66967698

