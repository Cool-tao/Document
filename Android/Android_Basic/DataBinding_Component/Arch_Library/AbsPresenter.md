> AbsPresenter
```
package org.alex.baselibrary.mvvm;

import org.alex.model.ObtainException;
import org.alex.util.ObjUtil;

/**
 * 作者：Alex
 * 时间：2017/10/29 08:06
 * 简述：
 */
abstract public class AbsPresenter<V extends AbsContract.View, M extends AbsContract.Model> implements AbsContract.Presenter {
    protected M model;
    protected V view;

    public AbsPresenter(V view) {
        this.view = view;
        model = createModel();
    }

    @SuppressWarnings("WeakerAccess")
    protected abstract M createModel();


    /**
     * 获取 数据 开始
     */
    @Override
    public void onObtainStart() {
        if(view!=null){
            view.showLoadingView();
        }
    }

    /**
     * 获取 数据 失败  出现异常
     */
    @Override
    public void onObtainFail(ObtainException ex) {
        if(view!=null){
            view.showToast(ex.getMessage());
        }
    }

    /**
     * 获取 数据 结束
     */
    @Override
    public void onObtainFinish() {
        if(view!=null){
            view.dismissLoadingView();
        }
    }

    /**
     * 销毁资源，防止内存泄漏
     */
    @Override
    public void onDestroy() {
        ObjUtil.setFieldNull(this);
    }
}
```