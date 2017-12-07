###### 点击其他区域键盘隐藏


```
// 以下代码 写在 Activity 
@Override
public boolean onTouchEvent(MotionEvent event) {
	if (event.getAction() == MotionEvent.ACTION_DOWN) {
		return hiddenKeyPad();
	}
	return false;
}
/**
 * 可以  关闭软键盘
 * 键盘 展示出来，则可以关闭， 返回 true；
 * 键盘 没有展示出来，则不可以关闭， 返回 false；
 */
public boolean hiddenKeyPad() {
	return KeyPadUtil.getInstance().hiddenKeyPad(activity);
}

@Override
protected void onStop() {
	super.onStop();
	hiddenKeyPad();
	ToastUtil.cancel();
}
```