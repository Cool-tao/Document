###### Instrumentation  

每一个应用程序只有一个Instrumentation对象，每个Activity内都有一个对该对象的引用。Instrumentation可以理解为应用进程的管家，  
ActivityThread要创建或暂停某个Activity时，都需要通过Instrumentation来进行具体的操作。  


