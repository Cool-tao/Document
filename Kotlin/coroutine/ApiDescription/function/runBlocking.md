##### runBlocking  

````
public fun <T> runBlocking(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T): T {
    val currentThread = Thread.currentThread()
    val eventLoop = if (context[ContinuationInterceptor] == null) BlockingEventLoop(currentThread) else null
    val newContext = newCoroutineContext(context + (eventLoop ?: EmptyCoroutineContext))
    val coroutine = BlockingCoroutine<T>(newContext, currentThread, privateEventLoop = eventLoop != null)
    coroutine.initParentJob(context[Job])
    block.startCoroutine(coroutine, coroutine)
    return coroutine.joinBlocking()
}
````
runBlocking函数不是用来当作普通协程函数使用的，它的设计主要是用来桥接普通阻塞代码和挂起风格的（suspending style）的非阻塞代码的；  

> 如果要在非 挂起函数内 使用 delay等挂起函数，需要用 runBlocking来修饰函数，例如下面的示例；  

```
private fun coroutineFun() = runBlocking<Unit> {
    val job0 = launch(UI) {
        LogTrack.w("job0 Start： ${Thread.currentThread()}")
        delay(3000L)
        LogTrack.w("job0 World!")
    }

    val job1 = launch(UI) {
        LogTrack.w("job1 Start：${Thread.currentThread()}")
        delay(5000L)
        LogTrack.w("job1 World!")
    }

    LogTrack.w("Main Thread: ${Thread.currentThread()}")
    LogTrack.w("job0 is active: ${job0.isActive}  ${job0.isCompleted}")
    LogTrack.w("job1 is active: ${job1.isActive}  ${job1.isCompleted}")
    //理论上这里是可以delay的，但是如果delay的时间比较长，就会报 ANR了；
    //= runBlocking<Unit>  如果函数或者 block里面，没有加 runBlocking修饰，那么是不可以使用delay函数的；
    delay(2000)
    LogTrack.w("我延时了2秒")
}
```
