###### startActivity 历程

1. Activity::4785  startActivity  
```
public void startActivity(Intent intent, @Nullable Bundle options) {
    if (options != null) {
        startActivityForResult(intent, -1, options);
    } else {
        // Note we want to go through this call for compatibility with
        // applications that may have overridden the method.
        startActivityForResult(intent, -1);
    }
}
```
2. FragmentActivity::724  startActivityForResult  
```
public void startActivityForResult(Intent intent, int requestCode) {
    if (!mStartedActivityFromFragment) {
        if (requestCode != -1) {
            checkForValidRequestCode(requestCode);
        }
    }
    super.startActivityForResult(intent, requestCode);
}
```
3. Activity::4429  startActivityForResult

4. BaseFragmentActivityApi16::45  startActivityForResult

5. Activity::4467  startActivityForResult(android.content.Intent, int, android.os.Bundle)  
```
public void startActivityForResult(@RequiresPermission Intent intent, int requestCode,
        @Nullable Bundle options) {
    if (mParent == null) {
        options = transferSpringboardActivityOptions(options);
        Instrumentation.ActivityResult ar =
            mInstrumentation.execStartActivity(
                this, mMainThread.getApplicationThread(), mToken, this,
                intent, requestCode, options);
     }
}
```

6. Instrumentation::1577  execStartActivity(android.content.Context, android.os.IBinder, android.os.IBinder, android.app.Activity, android.content.Intent, int, android.os.Bundle)  
```
...
// 1607
try {
        intent.migrateExtraStreamToClipData();
        intent.prepareToLeaveProcess(who);
        int result = ActivityManager.getService()
            .startActivity(whoThread, who.getBasePackageName(), intent,
                    intent.resolveTypeIfNeeded(who.getContentResolver()),
                    token, target != null ? target.mEmbeddedID : null,
                    requestCode, 0, null, options);
        /*最终执行 startActivity 的命令*/                    
        checkStartActivityResult(result, intent);
    } catch (RemoteException e) {
        throw new RuntimeException("Failure from system", e);
    }
```

7. Instrumentation::1923  checkStartActivityResult  
```


```