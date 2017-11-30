#### dalvik数据堆 与 jvm数据堆  

Dalvik将堆分成了Active堆和Zygote堆，这里大家只要知道Zygote堆是Zygote进程在启动的时候预加载的类、资源和对象；  
除此之外的所有对象都是存储在Active堆中的。对于为何要将堆分成Zygote和Active堆，这主要是因为Android通过fork方法创建到一个新的Zygote进程，  
为了尽可能的避免父进程和子进程之间的数据拷贝，fork方法使用写时拷贝技术，写时拷贝技术简单讲就是fork的时候不立即拷贝父进程的数据到子进程中，  
而是在子进程或者父进程对内存进行写操作时是才对内存内容进行复制，Dalvik的Zygote堆存放的预加载的类都是Android核心类和java运行时库，  
这部分内容很少被修改，大多数情况父进程和子进程共享这块内存区域。  
通常垃圾回收重点对Active堆进行回收操作，Dalvik为了对堆进行更好的管理创建了一个Card Table、两个Heap Bitmap和一个Mark Stack数据结构。  

