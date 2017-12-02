#### 执行顺序  

```
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        launch(UI) {
            getContent().await().logW()
            getName().await().logW()
        }
        return
    }
}

private suspend fun getName() =
        async {
            delay(5000)
            "耗时5秒，张三"
        }

private suspend fun getContent() =
        async {
            delay(2000)
            "耗时2秒，今天天气好"
        }

```
launch(UI)代码块内：  
getContent 耗时2秒，在其执行结束之前，后面的代码，不会被触发；  
在getContent之后，getName开始执行，耗时5秒；  
两个函数共计耗时7秒；  

