### Channel  

[示例1 ](../Channel/Sample01.md)   
#### receive()  
只接收一个事件  

#### for (result in channel)  
按顺序接收，处理所有事件  
```
for (result in channel) {
    "收到结果  $result".logI()
}
```
