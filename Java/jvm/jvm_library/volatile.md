###### volatile 关键字

Java语言是支持多线程的，为了解决线程并发的问题，在语言内部引入了 同步块 和 volatile 关键字机制。  
Java语言中的 volatile 变量可以被看作是一种“程度较轻的 synchronized”，与 synchronized 块相比，volatile 变量所需的编码较少，并且运行时开销也较少，  
但是它所能实现的功能也仅是synchronized 的一部分。  

volatile关键字的特点：  
1. [volatile 保证共享变量的可见性](volatile_visibility.md)；  
2. [禁用指令重排](volatile_ordering_instructions.md)；  
3. volatile并不能保证操作的原子性；  
4. 单独使用 volatile 还不足以实现计数器、互斥锁；  

要使 volatile 变量提供理想的线程安全，必须同时满足下面两个条件：    
1. 对变量的写操作不依赖于当前值。  
2. 该变量没有包含在具有其他变量的不变式中。  

第一个条件的限制使 volatile 变量不能用作线程安全计数器；  
虽然增量操作（x++）看上去类似一个单独操作，  实际上它是一个由读取－修改－写入操作序列组成的组合操作，  
必须以原子方式执行，而 volatile 不能提供必须的原子特性。实现正确的操作需要使 x 的值在操作期间保持不变，而 volatile 变量无法实现这点。  
然而，如果将值调整为只从单个线程写入，那么可以忽略第一个条件。  
在目前大多数的处理器架构上，volatile 读操作开销非常低 —— 几乎和非 volatile 读操作一样。而 volatile 写操作的开销要比非 volatile 写操作多很多，  
因为要保证可见性需要实现内存界定（Memory Fence），即便如此，volatile 的总开销仍然要比锁获取低。  
volatile 操作不会像锁一样造成阻塞，因此，在能够安全使用 volatile 的情况下，volatile 可以提供一些优于锁的可伸缩特性。  
如果读操作的次数要远远超过写操作，与锁相比，volatile 变量通常能够减少同步的性能开销。    

[volatile 示例](volatile_sample.md)    
状态标志  
一次性安全发布   





◆ 参考   
https://github.com/LRH1993/android_interview/blob/master/java/concurrence/volatile.md  
http://www.cnblogs.com/dolphin0520/p/3920373.html  
http://www.importnew.com/27002.html  
https://juejin.im/post/5a69f60d5188257350516be8  
