##### Parcelable 和 Serializable  


● Serializable（Java自带）：    
Serializable 用于 数据持久化、网络流；    
Serializable是序列化的意思，表示将一个对象转换成可存储或可传输的状态。序列化后的对象可以在网络上进行传输，也可以存储到本地；  
Serializable代码量少，写起来方便；  

● Parcelable（android 专用）：  
Parcelable 用于 内存间 传递数据；   
除了Serializable之外，使用Parcelable也可以实现相同的效果，  
不过不同于将对象进行序列化，Parcelable方式的实现原理是将一个完整的对象进行分解，  
而分解后的每一部分都是Intent所支持的数据类型，这样也就实现传递对象的功能了。  
Parcelable代码多一些，增减字段不方便；  


● 选择序列化方法的原则  
在使用内存的时候，Parcelable比Serializable性能高，所以推荐使用Parcelable；  
Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC；  
Parcelable不能使用在，将数据存储在磁盘上的情况，因为Parcelable不能很好的保证数据的持续性在外界有变化的情况下。  
尽管Serializable效率低点，但此时还是建议使用Serializable 。  

● Parcelable原理  
通过writeToParcel将JavaBean映射成Parcel对象，再通过createFromParcel将Parcel对象映射成你的对象，也可以将Parcel看成是一个流，  
通过writeToParcel把对象写到流里面，在通过createFromParcel从流里读取对象，只不过这个过程需要你来实现，因此写的顺序和读的顺序必须一致。  


