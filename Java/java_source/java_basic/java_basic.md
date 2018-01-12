###### Java的基本知识

◆ boolean（Boolean）  
虽然定义了boolean这种数据类型，但是只对它提供了非常有限的支持。在Java虚拟机中没有任何供boolean值专用的字节码指令，  
Java语言表达式所操作的boolean值，在编译之后都使用Java虚拟机中的int数据类型来代替，  
而boolean数组将会被编码成Java虚拟机的byte数组，  每个元素boolean元素占8位，  
这样我们知道 boolean 类型单独使用是4个字节，在数组中又是1个字节。  
那虚拟机为什么要用int来代替boolean呢？为什么不用byte或short，这样不是更节省内存空间吗？  
实际上，使用int的原因是，对于当下32位的CPU来说，一次进行32位的数据交换更加高效。  
《Java虚拟机规范》给出了“单独时使用4个字节，boolean数组时1个字节”的定义，具体还要看虚拟机实现是否按照规范来，  


◆ object中定义了哪些方法？
clone(), equals(), hashCode(), toString(), notify(), notifyAll(), wait(), finalize(), getClass()  

◆ 内存泄漏
长生命周期的对象持有短生命周期对象的引用， 使得被持有的短生命周期的对象，得不到释放，就很可能发生内存泄露；  

◆ final有哪些用法
♬ 被final修饰的类不可以被继承； 
♬ 被final修饰的方法不可以被重写； 
♬ 被final修饰的变量不可以被改变；  
♬ 被final修饰的方法，JVM会尝试将其内联，以提高运行效率；  
♬ 被final修饰的常量，在编译阶段会存入常量池中；  

[接口 和 抽象类](library/Interface_AbstractClass.md)  
[对Java中面向对象的认识](library/OOP.md)  
[对finalize方法的认识](library/finalize.md)  
[对象引用](library/Reference.md)  
[java位运算](library/BitOperation.md)  
