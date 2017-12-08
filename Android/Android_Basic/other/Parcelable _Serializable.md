##### Parcelable 和 Serializable  

● Serializable（Java自带）：    
Serializable是序列化的意思，表示将一个对象转换成可存储或可传输的状态。序列化后的对象可以在网络上进行传输，也可以存储到本地。  

● Parcelable（android 专用）：  
除了Serializable之外，使用Parcelable也可以实现相同的效果，  
不过不同于将对象进行序列化，Parcelable方式的实现原理是将一个完整的对象进行分解，  
而分解后的每一部分都是Intent所支持的数据类型，这样也就实现传递对象的功能了。  


