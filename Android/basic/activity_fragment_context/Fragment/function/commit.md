### commit  

◆ commit()  
在主线程中异步执行，其实也是 Handler 抛出任务，等待主线程调度执行。  
commit() 需要在宿主 Activity 保存状态之前调用，否则会报错。   
这是因为如果 Activity 出现异常需要恢复状态，在保存状态之后的 commit() 将会丢失，这和调用的初衷不符，所以会报错。  

◆ commitAllowingStateLoss()  
commitAllowingStateLoss() 也是异步执行，但它的不同之处在于，允许在 Activity 保存状态之后调用，也就是说它遇到状态丢失不会报错。  

◆ commitNow()  
commitNow() 是同步执行的，立即提交任务。  
FragmentManager.executePendingTransactions() 也可以实现立即提交事务。  
但我们一般建议使用 commitNow(), 因为另外那位是一下子执行所有待执行的任务，可能会把当前所有的事务都一下子执行了，这有可能有副作用。  
此外，这个方法提交的事务可能不会被添加到 FragmentManger 的后退栈，因为你这样直接提交，有可能影响其他异步执行任务在栈中的顺序。  
和 commit() 一样，commitNow() 也必须在 Activity 保存状态前调用，否则会抛异常。  
