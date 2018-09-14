### 首部字段说明  

◆ 请求：首部字段名 说明   
Accept 用户代理可处理的媒体类型  
Accept-Charset 优先的字符集  
Accept-Encoding 优先的内容编码  
Accept-Language 优先的语言（自然语言）  
Authorization Web认证信息  
Expect 期待服务器的特定行为  
From 用户的电子邮箱地址   
Host 请求资源所在服务器  
If-Match 比较实体标记（ETag）  
If-Modified-Since 比较资源的更新时间  
If-None-Match 比较实体标记（与If-Match相反）  
If-Range 资源未更新时发送实体Byte的范围请求  
If-Unmodified-Since 比较资源的更新时间（与If-Modified-Since相反）  
Max-Forwards 最大传输逐跳数  
Proxy-Authorization 代理服务器要求客户端的认证信息  
Range 实体的字节范围请求  
```
Range可以请求实体的部分内容，多线程下载一定会用到此请求头。例如：
表示头500个字节：bytes=0~499
表示第二个500字节：bytes=500~999
表示最后500字节：bytes=-500
表示500字节以后的范围：bytes=500-
```
Referer 对请求中URI的原始获取方  
TE 传输编码的优先级  
User-Agent HTTP 客户端程序的信息    

◆ 响应：首部字段名 说明   
Accept-Ranges 是否接受字节范围请求  
Age 推算资源创建经过时间  
ETag 资源的匹配信息  
Location 令客户端重定向至指定URI  
Proxy-Authenticate 代理服务器对客户端的认证信息  
Retry-After 对再次发起请求的时机要求  
Server HTTP服务器的安装信息  
Vary 代理服务器缓存的管理信息  
WWW-Authenticate 服务器对客户端的认证信息  

◆ 实体首部字段名 说明  
Allow 资源可支持的HTTP方法  
Content-Encoding 实体主体适用的编码方式  
Content-Language 实体主体的自然语言  
Content-Length 实体主体的大小（单位 ：字节）  
Content-Location 替代对应资源的URI  
Content-MD5 实体主体的报文摘要  
Content-Range 实体主体的位置范围  
Content-Type 实体主体的媒体类型  
Expires 实体主体过期的日期时间  
Last-Modified 资源的最后修改日期时间  

◆ Cache-Control指令一览    
no-cache 无 强制向源服务器再次验证  
no-store 无 不缓存请求或响应的任何内容  
max-age = [ 秒] 必需 响应的最大Age值  
max-stale( = [ 秒]) 可省略 接收已过期的响应  
min-fresh = [ 秒] 必需 期望在指定时间内的响应仍有效  
no-transform 无 代理不可更改媒体类型    
only-if-cached 无 从缓存获取资源  
cache-extension - 新指令标记（token）    
◆ 缓存响应指令  
public 无 可向任意方提供响应的缓存  
private 可省略 仅向特定用户返回响应  
no-cache 可省略 缓存前必须先确认其有效性  
no-store 无 不缓存请求或响应的任何内容  
no-transform 无 代理不可更改媒体类型  
must-revalidate 无 可缓存但必须再向源服务器进行确认  
proxy-revalidate 无 要求中间缓存服务器对缓存的响应有效性再进行确认  
max-age = [ 秒] 必需 响应的最大Age值  
s-maxage = [ 秒] 必需 公共缓存服务器响应的最大Age值  
cache-extension - 新指令标记（token）  
