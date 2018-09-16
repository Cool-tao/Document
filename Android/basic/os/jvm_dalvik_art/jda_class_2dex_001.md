##### 多个.class文件打包成.dex文件  
dalvik虚拟机执行的是.dex文件，jvm执行的是.class文件；    

JAVA程序经过编译，生成JAVA字节码保存在class文件中，JVM通过解码class文件中的内容来运行程序。  
而DVM运行的是Dalvik字节码，所有的Dalvik字节码由JAVA字节码转换而来，并被打包到一个DEX（Dalvik Executable）可执行文件中，  
DVM通过解释DEX文件来执行这些字节码。  

dalvik打包.class文件，方法数受限：多个class文件变成一个dex文件所带来的问题就是方法数超过65535时报错，由此引出MultiDex技术；  

class文件去冗余：class文件存在很多的冗余信息，dex工具会去除冗余信息，例如：多个class中的字符串常量合并为一个，  
每个class文件基本都有该字符常量，存在很大的冗余，并把所有的.class文件整合到.dex文件中。减少了I/O操作，提高了类的查找速度。  

