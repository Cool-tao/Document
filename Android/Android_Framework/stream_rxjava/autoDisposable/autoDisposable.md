```
autodispose       : "0.6.1",

uber_autodispose                                   : "com.uber.autodispose:autodispose:${versions.autodispose}",
uber_autodispose_android                           : "com.uber.autodispose:autodispose-android:${versions.autodispose}",
uber_autodispose_android_archcomponents            : "com.uber.autodispose:autodispose-android-archcomponents:${versions.autodispose}",
uber_autodispose_android_archcomponents_test       : "com.uber.autodispose:autodispose-android-archcomponents-test:${versions.autodispose}",
uber_autodispose_rxlifecycle                       : "com.uber.autodispose:autodispose-rxlifecycle:${versions.autodispose}",
uber_autodispose_kotlin                            : "com.uber.autodispose:autodispose-kotlin:${versions.autodispose}",
uber_autodispose_kotlin_android                    : "com.uber.autodispose:autodispose-android-kotlin:${versions.autodispose}",
uber_autodispose_kotlin_android_archcomponents     : "com.uber.autodispose:autodispose-android-archcomponents-kotlin:${versions.autodispose}",
uber_autodispose_kotlin_android_archcomponents_test: "com.uber.autodispose:autodispose-android-archcomponents-test-kotlin:${versions.autodispose}",

```

```
implementation libs.uber_autodispose
implementation libs.uber_autodispose_android
implementation libs.uber_autodispose_android_archcomponents
implementation libs.uber_autodispose_kotlin
implementation libs.uber_autodispose_kotlin_android
implementation libs.uber_autodispose_kotlin_android_archcomponents
```

```
Observable.interval(1, TimeUnit.SECONDS)
        .doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                LogTrack.i("Disposing subscription from onStart()");
            }
        })
        .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
        .subscribe(new LiteObserver<Long>() {
            @Override
            public void onNext(Long result) {
                LogTrack.i("Started in onStart(), running until onStop(): " + result);
            }
        });
```
```
model.getNewsList(req)
        .compose(RxHelper.defaultTransformer(view, this))
        .compose(RxHelper.wrap2DataTransformer<WrapperBean<List<NewsListItemBean>>, List<NewsListItemBean>>(this))
        .autoDisposable(view.androidLifecycleScopeProvide())
        .subscribe(LiteObserver {
        
        }
```