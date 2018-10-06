### Activity 常用方法  
[物理按键相关](keyDown.md)    
[SingleTask 模式 Intent传值](singleTask_intent.md)    
[startActivityForResult](startActivityForResult.md)    
[点击其他区域键盘隐藏](clickHideKeyboard.md)    
去掉Activity标题  
```
requestWindowFeature(Window.FEATURE_NO_TITLE);  
getActionBar().hide();  
```
开启全屏  
```
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
``` 

重启当前Activity    
```
Intent intent = getIntent();  
intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);  
overridePendingTransition(0, 0);  
finish();  
startActivity(intent);  
overridePendingTransition(0, 0);  
```
[不同App 的 Activity 之间的传值](otherApp_intent.md)    
[App假装进程包活](dummy_keepAlive.md)    
[关于墙壁纸](wallpaper.md)     
[关于输入法](softInputMode.md)    
禁止Activity截图  
```
getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);  
setContentView(getLayoutResId());
```
保持屏幕常亮  
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
}

/**
 * 这个方法的好处是不像唤醒锁（wake locks），需要一些特定的权限（permission）。  
  * 并且能正确管理不同app之间的切换，不用担心无用资源的释放问题。 
  */
```
