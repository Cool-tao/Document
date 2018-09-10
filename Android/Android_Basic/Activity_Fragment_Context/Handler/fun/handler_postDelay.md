### postDelay  

```
handler.enqueueMessage  
    messageQueue#enqueueMessage  
        
```
在if判断中：我们看到when==0或者when < p.when的情况下会执行if语句，即我们传入的绝对时间等于0或者绝对时间还没有到，那么该任务将会阻塞当前任务队列。  
此时我们看变量needWake被赋值为mBlocked.在next()方法内部，如果有阻塞，会把mBlocked这个变量标记为true。    
所以，此时needWake为true。从字面含义很容易理解，该标志为是否需要唤醒。  
然后看enqueueMessage方法的末尾：  
可以看到needWake此时为true，将调用native层的nativeWake方法，就是将MessageQueue唤醒。  
（1）前面由于我们提交了延时任务，由于时间未到，导致了当前MessageQueue被阻塞，needWake为true。  
（2）此时如果有新的任务被提交到MessageQueue，此时由于needWake为true，nativeWake方法被调用，新的任务被插入到队列头部，即延时任务的前面，MessageQueue.next方法被唤醒。  
（3）此时next取到刚刚新加的任务，如果没有延时会立刻交给Looper去处理。Looper处理完这个消息再次调用next()方法，MessageQueue继续读取消息链表。  
（4）如果延时任务还没到时间，计算一下剩余时间继续阻塞；直到阻塞时间到（即延时时间到）或者下一次有Message进队，此时else的方法将被处理，即处理当前的任务，此时整个流程就结束了。     
MessageQueue会根据post delay的时间排序放入到链表中，链表头的时间小，尾部时间最大。因此能保证时间Delay最长的不会block住时间短的。  
当每次post message的时候会进入到MessageQueue的next()方法，会根据其delay时间和链表头的比较，  
如果更短则，放入链表头，并且看时间是否有delay，  如果有，则block，等待时间到来唤醒执行，否则将唤醒立即执行。    
所以handler.postDelay并不是先等待一定的时间再放入到MessageQueue中，而是直接进入MessageQueue，以MessageQueue的时间顺序排列和唤醒的方式结合实现的。  


