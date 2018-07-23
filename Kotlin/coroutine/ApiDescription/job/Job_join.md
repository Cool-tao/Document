##### join函数  

挂起协程，一直等到job结束，当job正常执行一直到完成，挂起的协程会正常返回，不会抛出异常信息；  
当协程已经被取消，或者已经完成，再调用join函数，就会抛出异常；  

● 示例 1  
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
● 示例 2  
```
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {
        "click start ".logI()
        launch(UI) {
            "launch start ".logI()
            async { coroutineFun() }
            "launch finish ".logI()
        }
        "click finish ".logI()
        return
    }

}

private fun coroutineFun() = runBlocking {
    "A".logI()
    val job = launch(CommonPool) {
        "B".logI()
        delay(3000)
        "C".logI()
    }

    "F".logI()
    job.join()
    "D".logI()
    "E".logI()
}
```

所以示例2 的执行顺序是：  
click start   
click finish   
launch start   
launch finish   
A  
B  
F  
C  
D  
E  


