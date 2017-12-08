#### 常见命令  

● 查看栈顶的Activity  
adb shell dumpsys activity top  

● 查看连接设备  
adb devices  

● 查看进程  
adb shell ps  
USER    PID    PPID    VSIZE    RSS    WCHAN    PC    NAME    

● 杀死进程  
adb shell am force-stop com.alex.andfun.basic  

● 列出app包名列表  
-3	  只显示第三方应用  
-s	  只显示系统应用  
adb shell pm list packages  -3

● 获取android_id  
adb shell settings get secure android_id  

● 获取MAC地址   
adb shell cat /sys/class/net/wlan0/address  

● 获取序列号   
adb get-serialno  

● 获取CPU信息  
adb shell cat /proc/cpuinfo  

● 更多硬件与系统属性  
adb shell cat /system/build.prop  




