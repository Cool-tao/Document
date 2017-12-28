### ART与DVM在GC上的变化  

◆ 与 Dalvik 相比，ART 垃圾回收计划在很多方面都有一定的改善：  
与 Dalvik 相比，暂停次数从 2 次减少到 1 次。Dalvik 的第一次暂停主要是为了进行根标记，即在 ART 中进行并发标记，让线程标记自己的根，然后马上恢复运行。    
与 Dalvik 的另一个主要区别在于 ART GC 引入了移动垃圾回收器。  使用移动 GC 的目的在于通过堆压缩来减少后台应用使用的内存。  
目前，触发堆压缩的事件是 ActivityManager 进程状态的改变。当应用转到后台运行时，它会通知 ART 已进入不再“感知”卡顿的进程状态。  


◆ 参考  
https://source.android.google.cn/devices/tech/dalvik/gc-debug  
