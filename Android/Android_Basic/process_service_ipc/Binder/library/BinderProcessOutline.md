#### binder通信流程概述  
binder通信是一种client-server的通信结构，  
1.从表面上来看，是client通过获得一个server的代理接口，对server进行直接调用；  
2.实际上，代理接口中定义的方法与server中定义的方法是一一对应的；  
3.client调用某个代理接口中的方法时，代理接口的方法会将client传递的参数打包成为Parcel对象；  
4.代理接口将该Parcel发送给内核中的binder driver.  
5.server会读取binder driver中的请求数据，如果是发送给自己的，解包Parcel对象，处理并将结果返回；  
6.整个的调用过程是一个同步过程，在server处理的时候，client会block住。  

