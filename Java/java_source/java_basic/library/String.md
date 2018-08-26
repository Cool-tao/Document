### ==比较  
```
Q：下列程序的输出结果： 
String s1 = “abc”; 
String s2 = “abc”; 
System.out.println(s1 == s2); 
A：true，均指向常量池中对象。


Q：下列程序的输出结果： 
String s1 = new String(“abc”); 
String s2 = new String(“abc”); 
System.out.println(s1 == s2); 
A：false，两个引用指向堆中的不同对象。


Q：下列程序的输出结果： 
String s1 = “abc”; 
String s2 = “a”; 
String s3 = “bc”; 
String s4 = s2 + s3; 
System.out.println(s1 == s4); 
A：false，因为s2+s3实际上是使用StringBuilder.append来完成，会生成不同的对象。


Q：下列程序的输出结果： 
String s1 = “abc”; 
final String s2 = “a”; 
final String s3 = “bc”; 
String s4 = s2 + s3; 
System.out.println(s1 == s4); 
A：true，因为final变量在编译后会直接替换成对应的值，所以实际上等于s4=”a”+”bc”，而这种情况下，编译器会直接合并为s4=”abc”，所以最终s1==s4。
//  补充  
s1 = "ab" + "cd";
s2 = "abcd";    
System.out.println(s1 == s2);    // true



Q：下列程序的输出结果： 
String s = new String(“abc”); 
String s1 = “abc”; 
String s2 = new String(“abc”); 
System.out.println(s == s1.intern()); 
System.out.println(s == s2.intern()); 
System.out.println(s1 == s2.intern()); 
A：false，false，true。


Q：下列程序的输出结果： 
temp = "hh".intern();
s1 = "a" + temp;
s2 = "ahh";
System.out.println(s1 == s2);    // false

temp = "hh".intern();
s1 = ("a" + temp).intern();
s2 = "ahh";
System.out.println(s1 == s2);    // true


Q：下列程序的输出结果： 
String s3 = new String("1") + new String("1");    // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s3指向的堆中的对象（注此时常量池不会生成"11"）
s3.intern();    // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
String s4 = "11";    // jdk1.7之后，常量池中的地址其实就是s3的地址
System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true
```


###  参考  
https://www.cnblogs.com/Kidezyq/p/8040338.html  
