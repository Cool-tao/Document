###### Fragment Visible回调

> 在 ViewPager 中 用
```
@Override
public void setUserVisibleHint(boolean isVisibleToUser) {
	super.setUserVisibleHint(isVisibleToUser);
	 //LogUtil.e("我的财富当前可见 = "+isVisibleToUser);
		isVisible = isVisibleToUser;
		if(!isVisibleToUser){
			return ;
		}
}
```

	
> 在 layout中 用
```
@Override
public void onHiddenChanged(boolean hidden)
{
    super.onHiddenChanged(hidden);
    LogUtil.e("onHiddenChanged = "+hidden);
}
```
