##### 接口约束
> RxLifecycleView
```
public interface RxLifecycleView {
    Observable<LifeCycleEvent> lifecycle();

    <T> LifecycleTransformer<T> bindUntilEvent(LifeCycleEvent event);

    <T> LifecycleTransformer<T> bindToLifecycle();

    /**
     * 管理  rx 产生的 订阅信息
     */
    void addDisposable(Disposable disposable);
    void addDisposable(Subscription subscription);

}
```

> AbsContract
```
public interface AbsContract {
    interface ViewModelProvider<VM extends ViewModel> {
        VM createViewModel();
    }

    interface View extends ViewDispatcher {

    }

    interface ResourceProvider {

        int layoutResId();

        android.view.View layoutView();

        void onCreateData(Bundle savedInstanceState);

    }

    interface Presenter extends ObtainDispatcher {

        /**
         * 销毁资源，防止内存泄漏
         */
        void onDestroy();

    }

    interface PresenterProvider<P extends Presenter> {
        P createPresenter();
    }

    interface ViewModel {

        void onCreateData(Bundle savedInstanceState);

        void onClickEvent(android.view.View v);

        void onDestroy();


    }

    interface Model {

    }

}

```