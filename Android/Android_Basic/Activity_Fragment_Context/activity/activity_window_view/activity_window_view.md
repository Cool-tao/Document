#### 对Activity、Window、View的认识  

[Activity、Window、View层级嵌套结构](../../ImageFiles/awv_001.jpg)    

每一个Activity都包含了唯一一个PhoneWindow，这个就是Activity根Window；  
在它上面可以增加更多其他的Window，如dialog等；  

[三者，是怎样关联的](awv_001.md)  
  
DecorView  
Window  
PhoneWindow  
● 为什么要设计Activity、View、Window？  
Activity就像工匠，Window就像是窗户，View就像是窗花，LayoutInflater像剪刀，Xml配置像窗花图纸。  
Android根据他们不同的职能让他们各斯其活，同时也相互配合展示给我们灵活、精致的界面。  

● Window是什么？它的职能是什么？  
Activity要管理View需要通过Window来间接管理的。Window通过addView()、removeView()、updateViewLayout()这三个方法来管理View的。  

● View跟Window有什么联系？  
View需要通过Window来展示在Activity上。  

● Activity、View、Window三者如何关联？  
Activity包含了一个PhoneWindow，而PhoneWindow就是继承于Window的，Activity通过setContentView将View设置到了PhoneWindow上，    
View通过WindowManager的addView()、removeView()、updateViewLayout()对View进行管理。    
Window的添加过程以及Activity的启动流程都是一次IPC的过程。    
Activity的启动需要通过AMS完成；Window的添加过程需要通过WindowSession完成。  

> 参考  

http://www.jianshu.com/p/5297e307a688  
