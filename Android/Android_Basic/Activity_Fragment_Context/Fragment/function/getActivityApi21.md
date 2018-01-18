### getActivity()空指针问题  

◆ 搞清楚，为什么f.mActivity() 为空    
如果app长时间在后台运行，再次进入app的时候可能会出现crash，而且fragment会有重叠现象。如果系统内存不足、切换横竖屏、app长时间在后台运行，  
Activity都可能会被系统回收然后重建，但Fragment并不会随着Activity的回收而被回收，创建的所有 Fragment会被保存到Bundle里面，从而导致Fragment丢失对应的Activity。  

◆ 解决办法  

◆ Activity.onCreate  
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.module_order);
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    if (savedInstanceState != null) {
        currFmPosition = savedInstanceState.getInt("currFmPosition", 0);
    }
    fmNameList = new ArrayList<>(4);
    for (int i = 0; i < 4; i++) {
        Fragment fragment = new OrderFragment();
        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString("content", "我是第" + i + "个");
        argumentsBundle.putInt("position", i);
        fragment.setArguments(argumentsBundle);
        String fmTag = fragment.getClass().getSimpleName() + i;
        transaction.add(R.id.layoutBody, fragment, fmTag);
        fmNameList.add(fmTag);
        if (i != currFmPosition) {
            transaction.hide(fragment);
        }
    }
    transaction.commitAllowingStateLoss();
}
```
◆ Activity.onSaveInstanceState    
```
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (outState != null) {
        outState.putInt("currFmPosition", currFmPosition);
        String FRAGMENTS_TAG = "android:support:fragments";
        outState.remove(FRAGMENTS_TAG);
    }
}
```
◆ 参考  
http://blog.csdn.net/goodlixueyong/article/details/48715661  
