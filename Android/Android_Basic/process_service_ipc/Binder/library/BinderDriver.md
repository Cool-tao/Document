### Binder Driver  
尽管名叫‘驱动’，实际上和硬件设备没有任何关系，只是实现方式和设备驱动程序是一样的：它工作于内核态，  
提供open()，mmap()，poll()，ioctl()等标准文件操作，以字符驱动设备中的misc设备注册在设备目录/dev下，  用户通过/dev/binder访问该它。  
驱动负责进程之间Binder通信的建立，Binder在进程之间的传递，Binder引用计数管理，数据包在进程之间的传递和交互等一系列底层支持。  

Binder Driver会将自己注册成misc device，并向上层提供一个/dev/binder 节点，Binder节点对应的不是硬件设备，而是运行于内核态；  
Binder Driver的代码位于linux目录的drivers/misc/binder.c中；  
可以提供open(), ioctl(), mmap() 等常见的文件操作；  
◆ binder_open  
上层进程在访问Binder驱动是，首先就需要打开/dev/binder 节点，也就是利用binder_open() 函数；  
◆ binder_mmap  
mmap函数主要用于申请内存；  
◆ binder_ioctl  
这个是Binder接口函数中，工作量最大的一个函数，用于文件的读写操作；  
BINDER_WRITE_READ   读写操作；  
BINDER_SET_MAX_THREADS   设置最大线程数；  
BINDER_SET_CONTEXT_MGR   ServiceManager专用，变成上下文管理者；  

