ProcessState是以单例模式设计的。  
每个进程在使用binder机制通信时，均需要维护一个ProcessState实例来描述当前进程在binder通信时的binder状态。  
ProcessState有如下2个主要功能：  
1.创建一个thread,该线程负责与内核中的binder模块进行通信，称该线程为Pool thread；  
2.为指定的handle创建一个BpBinder对象，并管理该进程中所有的BpBinder对象。    
