##### produce  

启动一个新的协程，产生数据流，通过管道流发送数据；  
函数的返回值是ProducerJob类对象，即为协程对象；  
```
override fun onClick(v: View) {
    val id = v.id
    if (R.id.bt1 == id) {
        val job = launch(UI) {
            coroutineFun().consumeEach {
                LogTrack.w(it)
            }
            LogTrack.w("finish")
        }
        return
    }
}

private fun coroutineFun() = produce<String> {
    send("A")
    send("B")
    send("C")
    send("D")
}
```
