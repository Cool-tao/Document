### 优化稳定性、低功耗、性能优化    
● Application、Activity 启动的时候，不能有过多的操作，如果需要耗时操作，要用多线程处理；  
● 尽量多处理异常，保障应用不会出现crash；  
● 网络请求之前，先判断 网络是否通路， 否则不发起请求；  
● 大一点的对象，array、list、map，在不用的时候，需要及时clear；  
● 使用上下文相关，可以结合 弱引用；  
● 广播、EventBus之类的，也要及时解除注册；  
● 最好不要静态持有Activity，或者有 较长生命周期的对象，不要持有较短生命周期的对象引用，会造成内存泄漏；  
● 在onStop 或者 onPause 暂停  
```
对于循环动画，GPU不断刷新视图也是很耗电的；
对于一些 sensors 如gps监听等，也许要暂停；
```  
● 对于Bitmap或者本地的Drawable，最好先压缩处理再展示，或者用Glide等开源框架做处理；  
● 组合控件，自定义控件， 使用merge 优化层级结构；  
● 循环语句里面，不要重复造对象的引用，字符串拼接最好要使用StringBuild；  
● HashMap、ArrayList 初始化时，最好预估计其容量，扩容也是比较耗时的；  

> 参考  

https://www.jianshu.com/p/d5a843cb7ab1  
http://landerlyoung.github.io/blog/2014/10/31/androidzhong-de-wakelockshi-yong/  
http://www.jianshu.com/p/09d878e4c6ab  
http://blog.csdn.net/wh_19910525/article/details/8287202  
http://blog.csdn.net/airk000/article/details/9121003  
https://www.jianshu.com/p/d71b51a0e29f  

