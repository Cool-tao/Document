### join函数  

挂起协程，一直等到job结束，当job正常执行一直到完成或者被cancel，挂起的协程会正常返回，不会抛出异常信息；  
当协程已经被取消，或者已经完成，再调用join函数，就会抛出异常；  
当外部因素使用当前job，调用cancel函数，会使得job.join() 之后的代码快被执行；      

#### 示例 1  
```
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        coroutineFun()  // click 代码块
        return
    }
}

private fun coroutineFun() = runBlocking {
    "A".logI()
    val job = launch(UI) {  // launch
        "B".logI()
        delay(3000)
        "C".logI()
    }
    job.join()
    "D".logI()
    "E".logI()
}
```
在UI线程，不能直接使用job.join函数，要和async函数配合使用，因为join函数会让当前代码块挂起，一直等到它结束，才会执行join之后的代码块，  
由于UI线程是出于阻塞状态的，所以会导致UI线程被阻塞，直到ANR；  
所以coroutineFun 函数的调用者，需要使用async函数 调用coroutineFun函数；  
#### 示例 2  
```
fun testLaunch3() {
    "A主线程 $isUiThread".logI()
    launch {
        val job = launch(UI) {
            "B主线程 $isUiThread".logI()
            delay(5000L)
            "B 2主线程 $isUiThread".logI()
        }
        "C主线程 $isUiThread".logI()
        job.join()
        "D主线程 $isUiThread".logI()
        "E主线程 $isUiThread".logI()
    }
}
```
执行结果：  
```
2018-07-24 14:35:32.293 26571-26571/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:44) #testLaunch3] A主线程 true
2018-07-24 14:35:32.306 26571-26608/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:51) Sample04$testLaunch3$1#doResume] C主线程 false
2018-07-24 14:35:32.310 26571-26571/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:47) Sample04$testLaunch3$1$job$1#doResume] B主线程 true
2018-07-24 14:35:37.316 26571-26571/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:49) Sample04$testLaunch3$1$job$1#doResume] B 2主线程 true
2018-07-24 14:35:37.319 26571-26608/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:53) Sample04$testLaunch3$1#doResume] D主线程 false
2018-07-24 14:35:37.319 26571-26608/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:54) Sample04$testLaunch3$1#doResume] E主线程 false

```
#### 示例3
```
fun testLaunch4() {
    "A主线程 $isUiThread".logI()
    launch(UI) {
        val job = launch {
            delay(5000L)
            "B主线程 $isUiThread".logI()
            delay(5000L)
            "B1主线程 $isUiThread".logI()
            delay(8000L)
            "B2主线程 $isUiThread".logI()
            delay(5000L)
        }
        "C主线程 $isUiThread".logI()
        job.join()
        "D主线程 $isUiThread".logI()
        delay(6000L)
        "E主线程 $isUiThread".logI()
        job.cancel()
        "F主线程 $isUiThread".logI()
    }
}
```
执行结果  
```
2018-07-24 19:49:03.585 10445-10445/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:59) #testLaunch4] A主线程 true
2018-07-24 19:49:03.598 10445-10445/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:70) Sample04$testLaunch4$1#doResume] C主线程 true
2018-07-24 19:49:08.603 10445-10482/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:63) Sample04$testLaunch4$1$job$1#doResume] B主线程 false
2018-07-24 19:49:13.606 10445-10482/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:65) Sample04$testLaunch4$1$job$1#doResume] B1主线程 false
2018-07-24 19:49:21.608 10445-10482/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:67) Sample04$testLaunch4$1$job$1#doResume] B2主线程 false
2018-07-24 19:49:26.612 10445-10445/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:72) Sample04$testLaunch4$1#doResume] D主线程 true
2018-07-24 19:49:32.620 10445-10445/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:74) Sample04$testLaunch4$1#doResume] E主线程 true
2018-07-24 19:49:32.621 10445-10445/com.alex.andfun.coroutine I/LogTrack: [ (Sample04.kt:76) Sample04$testLaunch4$1#doResume] F主线程 true
```
