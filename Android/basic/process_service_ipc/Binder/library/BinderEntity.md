### Binder实体  
Binder实体，是各个Server以及ServiceManager在内核中的存在形式。  
Binder实体实际上是内核中binder_node结构体的对象，它的作用是在内核中保存Server和ServiceManager的信息，例如，Binder实体中保存了Server对象在用户空间的地址。  
简言之，Binder实体是Server在Binder驱动中的存在形式，内核通过Binder实体可以找到用户空间的Server对象。  
