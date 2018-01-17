### FragmentHostCallback  
◑ final FragmentController mFragments = FragmentController.createController(new HostCallbacks());

◑ FragmentActivity.HostCallbacks  
```
class HostCallbacks extends FragmentHostCallback<FragmentActivity> {
    public HostCallbacks() {
        super(FragmentActivity.this /*fragmentActivity*/);
    }
}
```
◑ FragmentHostCallback#FragmentHostCallback    
```
FragmentHostCallback(Activity activity, Context context, Handler handler,
        int windowAnimations) {
    mActivity = activity;
    mContext = context;
    mHandler = handler;
    mWindowAnimations = windowAnimations;
}
```