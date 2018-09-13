### Java的基本知识
◆ object中定义了哪些方法？
clone(), equals(), hashCode(), toString(), notify(), notifyAll(), wait(), finalize(), getClass()  
◆ 内存泄漏
长生命周期的对象持有短生命周期对象的引用， 使得被持有的短生命周期的对象，得不到释放，就很可能发生内存泄露；  
[数据类型](library/data_type.md)   
[boolean](library/boolean.md)  
[final](library/final.md)  
[接口 和 抽象类](library/Interface_AbstractClass.md)  
[对Java中面向对象的认识](library/OOP.md)  
[对finalize方法的认识](library/finalize.md)  
[对象引用](library/Reference.md)  
[java位运算](library/BitOperation.md)  
### 匿名内部类，引用外部变量需要 加上 final 修饰？？  
```
class Main$1 implements FunLisenter {
    Main$1(Main var1, String var2) {
        this.this$0 = var1;
        this.val$nameInner = var2;
    }

    public void fun() {
        System.out.println(this.val$nameInner);
    }
}
```
反编译之后，发现分明是两个对象，不是一个； 如果 外部变量不加上 final ，就需要 内部类 和 外部类，做变量的同步，  
结构体不需要同步，但是基础数据类型一定需要；好像解决不了，所以干脆 加上 final 就不需要同步了；    

