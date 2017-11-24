###### 手机横竖屏

> 竖屏
```
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
```

> 自动
```
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
setRequestedOrientation(isPortrait() ? 
ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
```
