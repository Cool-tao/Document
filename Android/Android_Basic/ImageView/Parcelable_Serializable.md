##### Parcelable 和 Serializable  
Parcelable 用于 内存间 传递数据；   
Serializable 用于 数据持久化、网络流；    

> 作用  

Serializable 的作用  
是为了保存对象的属性到本地文件、数据库、网络流、rmi以方便数据传输，当然这种传输可以是程序内的也可以是两个程序间的。  

Parcelable 的作用  
是因为Serializable效率过慢，为了在程序内不同组件间以及不同Android程序间(AIDL)高效的传输数据而设计，这些数据仅在内存中存在，Parcelable是通过IBinder通信的消息的载体。  

> 效率及选择  

Parcelable 的性能比 Serializable 好，在内存开销方面较小，所以在 内存间 数据传输时 推荐使用Parcelable，如activity间传输数据，而Serializable可将数据持久化方便保存，  
所以在需要保存或网络传输数据时选择Serializable，因为android不同版本Parcelable可能不同，所以不推荐使用Parcelable进行数据持久化；  


