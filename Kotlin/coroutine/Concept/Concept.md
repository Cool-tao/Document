### 协程相关概念  
协程：在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。  
为了避免阻塞操作，Kotlin提供了轻量级的、可控的，协程挂起的操作；  
协程是通过编译技术实现，不需要虚拟机或者操作系统的支持，通过在编译期插入相关代码来实现的；  
协程的主要目的，是将原来“利用Thread和回调函数实现的异步操作”，简化成看似同步的书写方式来实现；  
被suspend修饰的函数，称为挂起函数；  
挂起函数只能被挂起函数 或者 协程调用；  
基本上，每个挂起函数都转换为状态机；  

在挂起协程前，下一状态和相关局部变量等被存储在编译器生成的类字段中；  
在恢复该协程时，恢复局部变量并且状态机从挂起点接着后面的状态往后执行；  
被挂起的协程，是作为Continuation对象来存储和传递，Continuation中持有协程挂起状态与局部变量；  

线程是CPU的最小执行单元；
协程不是线程的取代者，而是抽象在线程之上的，协程需要线程来承载运行；  
协程是组织好的代码流程，但协程不会直接使用线程，协程直接利用的是执行器（Interceptor）；  
执行器可以关联任意线程或线程池, 可以使当前线程, UI线程, 或新建新程；  
实质上协程是一种轻量级的可控制的用户线程，基于状态机实现，有协程构造器启动；  

协程最早的描述是由Melvin Conway于1958年给出：“subroutines who act as the master program”(与主程序行为类似的子例程)。  
数据在后续调用中始终保持（ The values of data local to a coroutine persist between successive calls 协程的局部）  
当控制流程离开时，协程的执行被挂起，此后控制流程再次进入这个协程时，这个协程只应从上次离开挂起的地方继续   
（The execution of a coroutine is suspended as control leaves it, only to carry on where it left off when control re-enters the coroutine at some later stage）。  



> 参考  

http://blog.csdn.net/qq_32115439/article/details/74018755  
http://kotlinlang.org/docs/diagnostics/experimental-coroutines.html  

