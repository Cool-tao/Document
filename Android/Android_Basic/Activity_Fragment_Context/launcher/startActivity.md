### startActivity  
◆ Activity#startActivity  
```
@Override
public void startActivity(Intent intent, @Nullable Bundle options) {
    if (options != null) {
        startActivityForResult(intent, -1, options);
    } else {
        startActivityForResult(intent, -1);
    }
}
```
◆ Activity#startActivityForResult(intent, requestCode, options)  
```
public void startActivityForResult(@RequiresPermission Intent intent, int requestCode, Bundle options) {
    // 第一次启动Activity，mParent为空  
    if (mParent == null) {
        options = transferSpringboardActivityOptions(options);
        Instrumentation.ActivityResult ar = mInstrumentation.execStartActivity(
                this, mMainThread.getApplicationThread(), mToken, this,intent, requestCode, options);
        if (ar != null) {
            mMainThread.sendActivityResult(mToken, mEmbeddedID, requestCode, ar.getResultCode(),ar.getResultData());
        }
        ...
    } else {
        if (options != null) {
            mParent.startActivityFromChild(this, intent, requestCode, options);
        } else {
            mParent.startActivityFromChild(this, intent, requestCode);
        }
    }
}
```
◆ Activity#startActivityForResult(who, intent, requestCode, options)  
```
public void startActivityForResult(String who, Intent intent, int requestCode, Bundle options) {
    Uri referrer = onProvideReferrer();
    ... 
    options = transferSpringboardActivityOptions(options);
    Instrumentation.ActivityResult ar = mInstrumentation.execStartActivity(
            this, mMainThread.getApplicationThread(), mToken, who, intent, requestCode, options);
    if (ar != null) {
        mMainThread.sendActivityResult(mToken, who, requestCode, ar.getResultCode(), ar.getResultData());
    }
}
```
◆ Instrumentation#execStartActivity(who, contextThread, token, target, intent, requestCode, options)  
```
public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, 
    Activity target,Intent intent, int requestCode, Bundle options) {
    try {  
        // ... 1609 
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(who);
        int result = ActivityManager.getService()
            .startActivity(whoThread, who.getBasePackageName(), intent, intent.resolveTypeIfNeeded(who.getContentResolver()),
                    token, target != null ? target.mEmbeddedID : null, requestCode, 0, null, options);
        checkStartActivityResult(result, intent);
    } catch (RemoteException e) {
        throw new RuntimeException("Failure from system", e);
    }
}
```  

◆ ActivityManager#getService  
```
public static IActivityManager getService() {
    return IActivityManagerSingleton.get();
}

private static final Singleton<IActivityManager> IActivityManagerSingleton =
    new Singleton<IActivityManager>() {
        @Override
        protected IActivityManager create() {
            final IBinder b = ServiceManager.getService(Context.ACTIVITY_SERVICE);
            final IActivityManager am = IActivityManager.Stub.asInterface(b);
            return am;
        }
    };
```
◆ ActivityManagerNative.java::ActivityManagerProxy#startActivity  
```
public int startActivity(IApplicationThread caller, String callingPackage, Intent intent,
        String resolvedType, IBinder resultTo, String resultWho, int requestCode,
        int startFlags, ProfilerInfo profilerInfo, Bundle options) throws RemoteException {
    // ... 2394  
    mRemote.transact(START_ACTIVITY_TRANSACTION, data, reply, 0);
    // ... 
    return result;
}
```
◆ ActivityManagerNative#asInterface  
```
static public IActivityManager asInterface(IBinder obj) {
    // ...  
    IActivityManager in = (IActivityManager)obj.queryLocalInterface(descriptor);
    if (in != null) {
        return in;
    }
    //  obj = android.app.ActivityManagerNative#gDefault  
    return new ActivityManagerProxy(obj);
}
```
◆ 参考 
https://github.com/yipianfengye/androidSource/blob/master/14%20activity%E5%90%AF%E5%8A%A8%E6%B5%81%E7%A8%8B.md
