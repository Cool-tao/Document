#### 当按下电源键  

cpu上电，引导芯片的代码  开始从预定义的地方开始执行程序，加载引导程序 Bootloader 到ram中运行；  
Bootloader负责初始化硬件资源和加载 linux kernel，然后将控制权交给 linux kernel。    
linux kernel 得到控制权后，调用下列一系列函数：  

page_address_init();//页表地址初始化   
sched_init();   
page_alloc_init();//内存分配初始化，请看mmu和内存管理   
init_IRQ();//中断向量初始化   
softirq_init();//软中断初始化   
console_init();//终端初始化   
calibrate_delay();   
vfs_caches_init();//虚拟文件系统(vfs)初始化   
rest_init();//这就是最后创建init0进程的函数，init0进程会创建android的受精卵进程Zygote。  

[fork_linux kernel](../ImageFiles/launcher_001.png)  

讲到这，总结起来init进程主要做了三件事：   
1.创建一些文件夹并挂载设备；   
2.初始化和启动属性服务；   
3.解析init.rc配置文件并启动zygote进程；  


> 参考 

http://blog.csdn.net/mr_zhaojy/article/details/52776254    
http://blog.csdn.net/itachi85/article/details/54783506     
http://blog.csdn.net/hu3167343/article/details/38230271  
http://www.cnblogs.com/nokiaguy/archive/2013/04/14/3020774.html  
http://blog.jobbole.com/67931/  
http://blog.csdn.net/fu_kevin0606/article/details/53383031  
http://blog.csdn.net/sunao2002002/article/details/52454878  
http://blog.csdn.net/gaugamela/article/details/52133186  


 

