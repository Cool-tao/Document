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

[Http - Https 协议栈](../../ImageFiles/http_001.png)  
[Http - TCP 数据包](../../ImageFiles/http_002.png)  
[TCP连接所需要的套接字函数](../../ImageFiles/http_003.png)  
[TCP连接，客户端 与 服务端 交互](../../ImageFiles/http_004.png)  
◆ HTTP 通信传输流  
[HTTP请求与响应](../../ImageFiles/http_005.png)  
发送端在层与层之间传输数据时，每经过一层时必定会被打上一个该层所属的首部信息。   
反之，接收端在层与层传输数据时，每经过一层时会把对应的首部消去。  

与HTTP协议密切相关的协议：  IP、 TCP、 DNS  

◆ 各种协议与 HTTP 协议的关系  
[关系图](../../ImageFiles/http_007.png)  

