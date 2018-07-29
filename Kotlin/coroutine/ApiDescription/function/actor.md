### actor  

### 示例1
```
fun testActor() {

    val sendChannel = createSendChannel()
    launch {
        delay(1000L)
        sendChannel.send("100")
        delay(1500L)
        sendChannel.send("102")
        delay(2000L)
        sendChannel.send("106")
    }

}

private fun createSendChannel(): SendChannel<String> = actor<String>(UI) {
    for (message in channel) {
        "B主线程 $isUiThread， $message".logI()
    }
}
```
执行结果  
```
2018-07-29 22:11:59.933 9270-9270/com.alex.andfun.coroutine I/LogTrack: [ (Sample12.kt:32) Sample12$createSendChannel$1#doResume] B主线程 true， 100
2018-07-29 22:12:01.434 9270-9270/com.alex.andfun.coroutine I/LogTrack: [ (Sample12.kt:32) Sample12$createSendChannel$1#doResume] B主线程 true， 102
2018-07-29 22:12:03.437 9270-9270/com.alex.andfun.coroutine I/LogTrack: [ (Sample12.kt:32) Sample12$createSendChannel$1#doResume] B主线程 true， 106
```