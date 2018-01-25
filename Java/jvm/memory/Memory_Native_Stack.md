### 本地方法栈  

本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，  
其区别不过是虚拟机栈为虚拟机执行Java 方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的Native 方法服务。  
本地方法栈区域也可能会抛出StackOverflowError 和OutOfMemoryError异常。  

