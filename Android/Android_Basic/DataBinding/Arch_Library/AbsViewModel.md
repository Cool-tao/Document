> AbsViewModel
```
package org.alex.baselibrary.mvvm;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * 作者：Alex
 * 时间：2017/10/26 23:56
 * 简述：
 */
public abstract class AbsViewModel<V extends AbsContract.View, P extends AbsContract.Presenter> extends
        ViewModel implements
        AbsContract.ViewModel,
        AbsContract.PresenterProvider<P> {
    protected P presenter;
    protected V view;

    public AbsViewModel(V view) {
        this.view = view;
        presenter = createPresenter();
        bindView((FragmentActivity) view, (AbsContract.ResourceProvider) view);
    }

    public abstract P createPresenter();

    protected abstract void bindView(FragmentActivity activity, AbsContract.ResourceProvider resourceProvider);

    public abstract void onClickEvent(View v);

    public abstract void onCreateData(Bundle savedInstanceState);

}

```