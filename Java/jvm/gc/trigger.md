### 什么情况下触发垃圾回收  

GC有两种类型：Scavenge GC 和 Full  GC    

> Scavenge GC  

一般情况下，当新对象生成，并且在 Eden 申请空间失败时，就会触发 Scavenge  GC，    
对 Eden 区域进行GC，清除非存活对象，并且把尚且存活的对象移动到 Survivor 区，然后整理 Survivor 的两个区。    
这种方式的GC是对年轻代的 Eden 区进行，不会影响到年老代。因为大部分对象都是从 Eden 区开始的，同时 Eden 区不会分配的很大，所以 Eden 区的GC会频繁进行。    
因而，一般在这里需要使用速度快、效率高的算法，使 Eden 去能尽快空闲出来。  

> Full GC  

对整个堆进行整理，包括 Young、Old、Permanent。Full GC需要对整个对进行回收，所以比 Scavenge  GC要慢，因此应该尽可能减少Full GC的次数。  
在对JVM调优的过程中，很大一部分工作就是对于Full GC的调节。有如下原因可能导致Full GC：
· 年老代（Old Generation）被写满
· 持久代（Permanent Generation）被写满 
· System.gc()被显示调用 
·上一次GC之后Heap的各域分配策略动态变化

> 垃圾收集算法  



