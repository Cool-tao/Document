##### launch 的执行顺序

````
override fun onClick(v: View) {
    val id = v.id
    if (id == R.id.bt1) {  // IF  
        coroutineFun()  //  A
        launch(UI) {
            "我是独立的协程  start".logW()  //  B
            delay(2000)  //  C
            "我是独立的协程  finish".logW()  //  D
        }
        "我不在挂起函数内".logW()  //  E
        return
    }

}
````

● 最先执行的是A，因为是顺序执行的；  
● 之后执行 E，因为代码块 launch 是被挂起的UI线程，所以在编译的时候对应一个状态机，先存储在内存，先会被挂起，所以launch代码块会在当前顺序结构的最后被执行；  
● 之后执行B-C-D，是因为 IF 代码块的顺序结构都被执行完成了，所以开始执行被挂起的代码块；  
