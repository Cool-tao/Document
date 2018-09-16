##### 保持屏幕常亮  

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
}
```
这个方法的好处是不像唤醒锁（wake locks），需要一些特定的权限（permission）。  
并且能正确管理不同app之间的切换，不用担心无用资源的释放问题。  

