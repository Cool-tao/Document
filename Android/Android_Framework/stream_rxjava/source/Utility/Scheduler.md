### Scheduler  

subscribeOn 切换的是 调用subscribeOn之前的线程。  
observeOn 切换的是 调用observeOn之后的线程。  
observeOn之后，不可再调用subscribeOn 切换线程。  

只有第一subscribeOn() 起作用（所以多个 subscribeOn() 无意义）；  
observeOn() 可以使用多次，每个 observeOn() 将导致一次线程切换()，都是影响其后的操作的线程；  
不论是 subscribeOn() 还是 observeOn()，每次线程切换如果不受到下一个 observeOn() 的干预，线程将不再改变，不会自动切换到其他线程。  
如果没有调用过observableOn，那么所有的操作，都是受subscribeOn的影响，也就是第一次调用subscribeOn的影响；  
◆ 对应类    
Schedulers#io = IoScheduler  
Schedulers#newThread = NewThreadScheduler    
Schedulers#computation = ComputationScheduler    
Schedulers#trampoline = TrampolineScheduler    
Schedulers#single = SingleScheduler    

◆ 示例  
只有第一subscribeOn() 起作用（所以多个 subscribeOn() 无意义）；  
```
Observable.just("你好")
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(new Function<String, String>() {
            @Override
            public String apply(String inValue) throws Exception {
                LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  //true  主线程
                return inValue + "，被修改了";
            }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .map(new Function<String, String>() {
            @Override
            public String apply(String inValue) throws Exception {
                LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  //true  主线程
                return inValue + "，被修改了";
            }
        })

Observable.just("你好")
        .subscribeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .map(new Function<String, String>() {
            @Override
            public String apply(String inValue) throws Exception {
                LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  //false  子线程
                return inValue + "，被修改了";
            }
        })
        .subscribeOn(AndroidSchedulers.mainThread())
        .map(new Function<String, String>() {
            @Override
            public String apply(String inValue) throws Exception {
                LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  //false  子线程
                return inValue + "，被修改了";
            }
        })
```
◆ 示例  
observeOn() 可以使用多次，每个 observeOn() 将导致一次线程切换()，都是影响其后的操作的线程；    
```
Observable.just("你好")
                .observeOn(Schedulers.io())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String inValue) throws Exception {
                        LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  // false 子线程
                        return inValue + "，被修改了";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String inValue) throws Exception {
                        LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  // true  主线程
                        return inValue + "，被修改了";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new LiteObserver<String>() {
                    @Override
                    public void onNext(String result) {
                        LogTrack.i(Looper.myLooper() == Looper.getMainLooper());  // false 子线程
                        LogTrack.i(result);
                    }
                });
```

