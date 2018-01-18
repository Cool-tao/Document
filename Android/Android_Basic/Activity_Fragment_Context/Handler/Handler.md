### Handler  
[一个Handler的标准写法](fun/handler_fun.md)  
首先需要了解几个类： Handler  Looper  Message  MessageQueue   

创建 handler 的时候会调用looper.prepare()来创建一个 looper，  
handler 通过 send 发送消息 (sendMessage) ,当然 post 一系列方法最终也是通过 send 来实现的，    
在 send 方法中handler 会通过 enqueueMessage() 方法中的 enqueueMessage(msg,millis )向消息队列 MessageQueue 插入一条消息，   
同时会把本身的 handler 通过 msg.target = this 传给message，    
Looper 是一个死循环，不断的读取MessageQueue中的消息，loop 方法会调用 MessageQueue 的 next 方法来获取新的消息，  
next 操作是一个阻塞操作,当没有消息的时候 next 方法会一直阻塞，进而导致 loop 一直阻塞，  
当有消息的时候,Looper 就会处理消息 Looper 收到消息之后就开始处理消息: msg.target.dispatchMessage(msg),当然这里的 msg.target 就是上面传过来的发送这条消息的 handler 对象，  
这样 handler 发送的消息最终又交给他的dispatchMessage方法来处理了,这里不同的是,handler 的 dispatchMessage 方法是在创建 Handler时所使用的 Looper 中执行的，  
这样就成功的将代码逻辑切换到了主线程了，Handler 处理消息的过程是:首先,检查Message 的 callback 是否为 null,不为 null 就通过 handleCallBack 来处理消息，  
Message 的 callback 是一个 Runnable 对象,实际上是 handler 的 post 方法所传递的 Runnable 参数.其次是检查 mCallback 是 否为 null,不为 null 就调用 mCallback 的handleMessage 方法来处理消息.


◆ 参考  
http://wangkuiwu.github.io/2014/08/26/MessageQueue/  
http://blog.csdn.net/lmj623565791/article/details/38377229  
https://zhuanlan.zhihu.com/p/29929031  
http://hpw123.win/2017/01/04/Handler%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90/  


