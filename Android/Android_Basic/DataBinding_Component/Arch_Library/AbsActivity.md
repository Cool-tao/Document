> AbsActivity
```
package org.alex.baselibrary.mvvm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleTransformer;

import org.alex.rxjava.lifecycle.FragmentLifeCycleEvent;
import org.alex.rxjava.lifecycle.LifeCycleEvent;
import org.alex.rxjava.lifecycle.LifecycleHelper;
import org.alex.util.ToastUtil;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 作者：Alex
 * 时间：2017/8/15 
 * 简述：
 */
public abstract class AbsActivity<VM extends AbsContract.ViewModel> extends AppCompatActivity implements
        AbsContract.ResourceProvider,
        AbsContract.ViewModelProvider<VM>,
        AbsContract.View{
    protected Context context;
    protected Activity activity;
    protected BehaviorSubject<LifeCycleEvent> lifecycleSubject = BehaviorSubject.create();
    private CompositeDisposable compositeDisposable;
    private List<Subscription> subscriptionList;
    protected VM viewModel;

    /**
     * 配置 布局文件的 资源 id
     */
    @Override
    public abstract int layoutResId();

    @Override
    public View layoutView() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        lifecycleSubject.onNext(FragmentLifeCycleEvent.CREATE);
        viewModel = createViewModel();
        onGetIntentData(getIntent());
        onCreateData(savedInstanceState == null ? new Bundle() : savedInstanceState);
        viewModel.onCreateData(savedInstanceState == null ? new Bundle() : savedInstanceState);
    }

    /**
     * 获取上个页面 传递的数据
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            onGetIntentData(intent);
        }
    }

    /**
     * 获取启动者通过Intent传递过来的 数据
     */
    @SuppressWarnings("UnusedParameters")
    void onGetIntentData(Intent intent) {

    }


    @SuppressWarnings("unused")
    protected Boolean autoFinish() {
        return true;
    }

    @Override
    public   void showToast(Object text) {
        ToastUtil.shortCenter(text);
    }

    @Override
    protected void onStart() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.RESUME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(FragmentLifeCycleEvent.STOP);
        super.onStop();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Observable<LifeCycleEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(LifeCycleEvent event) {
        return com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return com.trello.rxlifecycle2.RxLifecycle.bind(lifecycleSubject, LifecycleHelper.activityLifecycle());
    }

    /**
     * 管理  rx 产生的 订阅信息
     */
    @Override
    public void addDisposable(Disposable disposable) {
        compositeDisposable = (compositeDisposable == null) ? new CompositeDisposable() : compositeDisposable;
        compositeDisposable.add(disposable);
    }

    @Override
    public void addDisposable(Subscription subscription) {
        subscriptionList = (subscriptionList == null) ? new ArrayList<Subscription>() : subscriptionList;
        subscriptionList.add(subscription);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroy() {
        activity = null;
        context = null;
        lifecycleSubject.onNext(FragmentLifeCycleEvent.DESTROY);
        if (viewModel != null) {
            viewModel.onDestroy();
        }
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        if (subscriptionList != null) {
            for (int i = 0; i < subscriptionList.size(); i++) {
                subscriptionList.get(i).cancel();
            }
            subscriptionList.clear();
            subscriptionList = null;
        }
        super.onDestroy();
    }
}

```