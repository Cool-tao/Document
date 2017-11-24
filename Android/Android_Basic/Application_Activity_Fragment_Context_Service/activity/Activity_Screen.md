###### 禁止Activity截图
```
getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
setContentView(getLayoutResId());
```