### 对Activity、Window、View的认识  

[Activity、Window、View层级嵌套结构](../../Activity_Fragment_Context/ImageFiles/awv_001.jpg)    

每一个Activity都包含了唯一一个PhoneWindow，这个就是Activity根Window；  
在它上面可以增加更多其他的Window，如dialog等；  

● 为什么要设计Activity、View、Window？  
View 是视图， window是面板，视图需要挂在到面板上；Activity负责管理面板的生命周期；  
[三者，是怎样关联的](library/awv_001.md)  
[Activity存在的必要](library/Necessity_Activity.md)  
DecorView  
[Window](library/Necessity_Window.md)    
PhoneWindow  
WindowManagerService  
WindowManager  


● Window是什么？它的职能是什么？  
Activity 内部持有 PhoneWindow 对象， 
Activity 通过Window来管理View，Window通过addView()、removeView()、updateViewLayout()这三个方法来管理View的。  

● View跟Window有什么联系？  
View需要通过Window来展示在Activity上。  

● Activity、View、Window三者如何关联？  
[Activity包含了一个PhoneWindow](library/awv_003.md)  ，
View通过WindowManager的addView()、removeView()、updateViewLayout()对View进行管理。    
Window的添加过程以及Activity的启动流程都是一次IPC的过程。    
Activity的启动需要通过AMS完成；Window的添加过程需要通过WindowSession完成。  

WindowManager  
WindowManager为每个Window创建Surface对象，然后应用就可以通过这个Surface来绘制任何它想要绘制的东西。而对于WindowManager来说，这只不过是一块矩形区域而已。  

> 参考  

http://blog.csdn.net/huachao1001/article/details/51866287  
http://liuwangshu.cn/framework/wm/1-windowmanager.html  
http://liuwangshu.cn/framework/wm/2-window-property.html  
http://liuwangshu.cn/framework/wm/3-add-window.html  
http://liuwangshu.cn/framework/wms/1-wms-produce.html  
http://liuwangshu.cn/framework/wms/2-wms-member.html  
http://liuwangshu.cn/framework/wms/3-wms-remove.html  

