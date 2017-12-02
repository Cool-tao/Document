#### 入门示例  01  

● 延时2秒之后，吐司提示  
```
import kotlinx.coroutines.experimental.android.UI

override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        coroutineFun()
        return
    }

}

private fun coroutineFun() {
    launch(UI) {
        delay(2000L)●
        ToastUtil.shortCenter("hello")
    }
}
```

● suspend  挂起函数  
```
private fun coroutineFun() {
    LogTrack.w(Looper.myLooper()== Looper.getMainLooper())//true
    launch(UI) {
        foo()
    }
    ToastUtil.shortCenter("我是UI线程")
    LogTrack.w(Looper.myLooper()== Looper.getMainLooper())//true
}

private suspend fun foo() {
    LogTrack.w(Looper.myLooper()== Looper.getMainLooper())//true
    delay(2000L)
    ToastUtil.shortCenter("hello")
    LogTrack.w(Looper.myLooper()== Looper.getMainLooper())//true
}
```

● async 与 Deferred  
```
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        LogTrack.w(Looper.myLooper() == Looper.getMainLooper())//true
        launch(UI) {
            LogTrack.w(Looper.myLooper() == Looper.getMainLooper())//true
            val value = coroutineFun().await()//挂起点，2秒后，状态恢复，代码顺序执行
            ToastUtil.shortCenter(value)
            LogTrack.w(Looper.myLooper() == Looper.getMainLooper())//true
        }
        return
    }

}

private suspend fun coroutineFun() =
        async {
            LogTrack.w(Looper.myLooper() == Looper.getMainLooper())//false
            delay(2000)
            LogTrack.w(Looper.myLooper() == Looper.getMainLooper())//false
            "hello coroutine"
        }
```