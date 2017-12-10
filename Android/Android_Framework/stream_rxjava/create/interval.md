#### interval  

```
Observable.interval(1000, TimeUnit.MILLISECONDS)
        .compose(RxHelper.schedulersTransformer())
        .compose(RxHelper.addLifecycleTransformer(this))
        .compose(bindUntilEvent(ActivityLifeCycleEvent.STOP))
        .subscribe {
            LogTrack.w(it)
        }
```
