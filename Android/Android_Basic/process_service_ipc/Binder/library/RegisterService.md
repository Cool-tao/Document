### Server注册到ServiceManager中  
Server首先会向Binder驱动发起注册请求，而Binder驱动在收到该请求之后就将该请求转发给ServiceManager进程。  
但是Binder驱动怎么才能知道该请求是要转发给ServiceManager的呢？  
这是因为Server在发送请求的时候，会告诉Binder驱动这个请求是交给0号Binder引用对应的进程来进行处理的。而Binder驱动中指定了0号引用是与ServiceManager对应的。  
在Binder驱动转发该请求之前，它其实还做了两件很重要的事：  
(01) 当它知道该请求是由一个Server发送的时候，它会新建该Server对应的Binder实体。   
(02) 它在ServiceManager的"保存Binder引用的红黑树"中查找是否存在该Server的Binder引用；  
找不到的话，就新建该Server对应的Binder引用，并将其添加到"ServiceManager的保存Binder引用的红黑树"中。  
简言之，Binder驱动会创建Server对应的Binder实体，并在ServiceManager的红黑树中添加该Binder实体的Binder引用。  
当ServiceManager收到Binder驱动转发的注册请求之后，它就将该Server的相关信息注册到"Binder引用组成的单链表"中。  
这里所说的Server相关信息主要包括两部分：Server对应的服务名 + Server对应的Binder实体的一个Binder引用。  

