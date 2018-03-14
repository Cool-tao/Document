#### 常见命令  

● 查看栈顶的Activity  
adb shell dumpsys activity top  

● 计算MainActivity启动时间  
adb shell am start -W com.alex.andfun.basic/com.alex.andfun.basic.MainActivity  
如果只关心某个应用自身启动耗时，参考TotalTime；  
如果关心系统启动应用耗时，参考WaitTime；  
如果关心应用有界面Activity启动耗时，参考ThisTime；  

● 安装app  
adb devices    
adb -s emulator-5556 install    
adb -s emulator-5556 install ebook.apk   

● 查看连接设备  
adb devices  

● 查看adb 版本信息  
adb version  

● 退出当前环境  
exit  

● 进入shell环境  
adb shell   

● 查看进程  
adb shell  
ps  
// 结果
USER    PID    PPID    VSIZE    RSS    WCHAN    PC    NAME    

● 查看指定包名的进程  
adb shell   
ps|grep com.alex.andfun.baisc  

● 按照包名 杀死进程  
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




