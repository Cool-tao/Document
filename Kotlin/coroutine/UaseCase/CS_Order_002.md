#### 并发顺序  

```
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        launch(UI) {
            LogTrack.w(TimeUtil.getFormatTime(System.currentTimeMillis()))  // A  
            val deferred0 = async(CommonPool) { getContent() }
            val deferred1 = async(CommonPool) { getName() }
            listOf(deferred0, deferred1).forEach {
                it.join();
                TimeUtil.getFormatTime(System.currentTimeMillis()).logW() // B
            }
        }
        return
    }
}

private suspend fun getName(): String {
    delay(5000)
    return "耗时5秒，张三"
}

private suspend fun getContent(): String {
    delay(2000)
    return "耗时2秒，今天天气好"
}
```

launch(UI)代码块内：  
async是挂起函数，并不影响其他代码块的执行顺序；  
getContent 和 getName 几乎是同时执行；  
getContent 耗时2秒， getName 耗时5秒； 
两个函数共计耗时5秒；  
