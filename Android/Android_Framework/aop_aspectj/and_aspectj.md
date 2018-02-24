###  aspectj  

[gradle配置](library/and_build.md)  

[监控 某个Activity启动](library/sample_001.md)  

假设插入的方法是 fooA;  目标方法是 funA;  
call 和 execution  
execution  插入的 代码，在目标 方法  内部， 形成  
```
funA(){
    fooA();
    // 实际 方法体  
}
funA(){
    // 实际 方法体  
    fooA();
}
```
call 插入的 代码，在目标 方法  外部， 形成  
```
methodA(){
    fooA();
    funA();  
}
methodA(){
    funA();  
    fooA();
}
```

@Before("execution(* android.app.Activity.onCreate(..))")  
@Before("execution(* android.app.Activity.on*(..))")  
第一个『*』表示返回值，『*』表示返回值为任意类型  
第二个『*』来进行通配，几个『*』没区别。  

java.lang.String  匹配String类型  
java.*.String  
匹配java包下的任何“一级子包”下的String类型，如匹配java.lang.String，但不匹配java.lang.ss.String  

java..*  
匹配java包及任何子包下的任何类型,如匹配java.lang.String、java.lang.annotation.Annotation  

java.lang.*ing  
匹配任何java.lang包下的以ing结尾的类型  

java.lang.Number+  
匹配java.lang包下的任何Number的自类型，如匹配java.lang.Integer，也匹配java.math.BigInteger  


com.alex.andfun..*.Activity+.onCreate  


◆  参考  
https://www.jianshu.com/p/5c9f1e8894ec  
http://blog.csdn.net/eclipsexys/article/details/54425414  
https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx  
https://www.jianshu.com/p/f90e04bcb326  
