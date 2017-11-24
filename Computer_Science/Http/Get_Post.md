###### GET与 POST 的区别

- get 请求  
参数在长链接之后 的 ? 之后，用key=value形式并用&符号分割；
如果要传中文参数，必须要用URLEncoding  和 URLDecoding 进行交互；
不能添加body；
- POST请求  
参数可以放在长链接之后，和GET请求一样；  
参数可以放在并body，参数的编码形式，要和后端统一，最好要用UTF-8；  
 