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

> 横竖屏切换的生命周期  

onCreate...  
onStart...  
onResume..  

#### 由    竖屏  切换成   横屏  
onPause..  
onSaveInstanceState..  
onStop..  
onDestroy..  
onCreate...  
onStart...  
onRestoreInstanceState..  
onResume..  

#### 由    横屏  切换成   竖屏  
onPause..  
onSaveInstanceState..  
onStop..  
onDestroy..  
onCreate...  
onStart...  
onRestoreInstanceState..  
onResume..  

#### 可以通过在AndroidManifest文件的Activity中指定如下属性：  

android:configChanges = "orientation|screenSize"    

onCreate...  
onStart...  
onResume..  
由    竖屏  切换成   横屏  
onConfigurationChanged..  
由    横屏  切换成   竖屏  
onConfigurationChanged..  








