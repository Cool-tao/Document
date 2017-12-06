###### 关于墙壁纸
> 获取当前壁纸
```
WallpaperManager wm=WallpaperManager.getInstance(this);
Drawable wallpaper=wpm.getDrawable();
```
> 设置当前壁纸
```
imapaper.setDrawingCacheEnabled(true);
Drawable drawale=this.getResources().getDrawable(R.drawable.bg);
imapaper.setImageDrawable(drawale);
wpm.setBitmap(imapaper.getDrawingCache());
``` 

```
<uses-permission android:name="android.permission.SET_WALLPAPER"/>
```