### 连接管理  
Http连接实际上就是TCP连接，和一些使用TCP连接的规则；  
◑ 解析出主机名；  
◑ 查询这个主机名对应的IP地址；  
◑ 获取端口号；  
◑ 发送连接到对应的 主机 : 端口；  
◑ 发送GET等请求；  
◑ 收到服务端的响应报文；  
◑ 关闭连接；  

 ◆ 多条连接如何区分？  
 每条TCP连接都会包含<源IP地址、源端口号、目的IP地址、目的端口号>  

[Http - Https 协议栈](../ImageFiles/http_001.png)  
[Http - TCP 数据包](../ImageFiles/http_002.png)  
[TCP连接所需要的套接字函数](../ImageFiles/http_003.png)  
[TCP连接，客户端 与 服务端 交互](../ImageFiles/http_004.png)  


