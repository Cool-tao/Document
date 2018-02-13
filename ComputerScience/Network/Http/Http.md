### Http相关知识  
HTTP 是一种不保存状态，即无状态（stateless）协议。 HTTP 协议自身不对请求和响应之间的通信状态进行保存。   
也就是说在 HTTP 这个级别，协议对于发送过的请求或响应都不做持久化处理。    
这是为了更快地处理大量事务，确保协议的可伸缩性，而特意把 HTTP 协议设计成如此简单的。  
但为了实现期望的保持状态功能，于是引入了 Cookie 技术。有了 Cookie 再用 HTTP 协议通信，就可以管理状态了。  

[请求方式的区别](library/RequestMethod.md)  
[状态码](library/http_code.md)  
[一次完整的网络请求过程](library/a_complete_network_request_process.md)    
[HTTP报文](library/Message.md)  

