## Java代码规范

> 对于集合框架、StringBuild等可变数据类型，最好给初始容量

向HashMap 或者 StringBuild 在扩容的时候是比较浪费时间的，所以在使用的时候，尽量能预估计其容量；  

> 多多使用 final

如果一个 属性、变量，不会被二次实例化，最好用final 修饰；  
如果一个函数，较少次被引用，最好用 final 修饰，在java编译期，会被inline 过去，    
不会出现方法调用的 入栈 和 出栈操作，等于说是拿空间换时间；    

> 尽量用 int 或者 注解 来代替枚举，尤其是来代替boolean  

首先， 枚举比较占内存；然后 boolean在JVM本身中本身就是用 1 和 非1  来代替的；  
其次 boolean 之后 两种状态， 很多业务需求，最早只有2种状态，后续就变成 多种状态；  

> 最好不要在 for 循环中 创建引用  

尽量用第二种编码方式， 减少对象引用的存在；

1. 第一种编码方式
```
List<UserBen> list = new ArrayList<UserBean>(10);
for 0.. 10{
    UserBean bean = new UserBean();
    bean.set();
    ...
    list.add(bean)
}
```
 2. 第二种编码方式
```
List<UserBen> list = new ArrayList<UserBean>(10);
UserBean bean = null;
for 0.. 10{
    bean = new UserBean();
    bean.set();
    ...
    list.add(bean)
}
```

> 多使用方法调用， 减少直接修改全局变量

1. 首先 方法的入栈 出栈 是基于 栈内存的， 是线程私有的； 
2. 假设出现数据问题，例如 现在有个 activityCode ，被 50次 修改了，但是并不知道在哪里修改的，   
如果每次修改 activityCode 都是 通过方法调用，而不是直接对 全局变量进行操作   
这个时候， 在 updateActivityCode 打印方法调用栈，就能排查 是谁修改了 activityCode； 

> 方法的参数，个数最好不要超过4个

如果参数过多， 最好要有对应的 paramsEntity；  
例如前端后端交互的时候， 最好使用 reqEntity  和 respEntity 来代替 getFoo(String name, String pwd, String label, String category ... )

> 关于GC  

对于不用的 变量 userEntity  最好不要 使用 userEntity = null; 来假装优化， 但是千万不要手动调用 System.gc();    
置null的做法对GC的帮助微乎其微，有时候反而会导致代码混乱，手动gc还会影响性能；

> 参考 
- http://ifeve.com/gc-oriented-java-programming/
