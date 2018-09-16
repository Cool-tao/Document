### Binder引用  
说到Binder实体，就不得不说"Binder引用"。所谓Binder引用，实际上是内核中binder_ref结构体的对象，它的作用是在表示"Binder实体"的引用。  
换句话说，每一个Binder引用都是某一个Binder实体的引用，通过Binder引用可以在内核中找到它对应的Binder实体。  
如果将Server看作是Binder实体的话，那么Client就好比Binder引用。Client要和Server通信，它就是通过保存一个Server对象的Binder引用，  
再通过该Binder引用在内核中找到对应的Binder实体，进而找到Server对象，然后将通信内容发送给Server对象。  
Binder实体和Binder引用都是内核(即，Binder驱动)中的数据结构。  
每一个Server在内核中就表现为一个Binder实体，而每一个Client则表现为一个Binder引用。  
这样，每个Binder引用都对应一个Binder实体，而每个Binder实体则可以多个Binder引用。  


