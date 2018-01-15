### Binder Driver  
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

