#### MultiDex  

因为在Dalvik指令集里，调用方法的invoke-kind指令中，method reference index只给了16bits，最多能调用65535个方法，  
所以在生成dex文件的过程中，  当方法数超过65535就会报错。细看指令集，除了method，field和class的index也是16bits，所以也存在65535的问题。  
一般来说，method的数目会比field和class多，所以method数会首先遇到65535问题，你可能都没机会见到field过65535的情况。  


> 参考  

http://jiajixin.cn/2015/10/21/field-65535/  
http://www.jianshu.com/p/33f22b21ef1e  

