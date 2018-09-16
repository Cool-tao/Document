
DexClassLoader ：可以加载文件系统上的jar、dex、apk  
PathClassLoader ：可以加载/data/app目录下的apk，这也意味着，它只能加载已经安装的apk  
URLClassLoader ：可以加载java中的jar，但是由于dalvik不能直接识别jar，所以此方法在android中无法使用，尽管还有这个类  
