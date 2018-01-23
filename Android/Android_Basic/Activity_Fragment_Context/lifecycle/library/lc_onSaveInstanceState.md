### onSaveInstanceState

为什么 在 onStop 之前执行了 onSaveInstanceState  

◆ ActivityThread::4132  handleStopActivity 方法  
```
private void handleStopActivity(IBinder token, boolean show, int configChanges, int seq) {
    ActivityClientRecord r = mActivities.get(token);
    if (!checkAndUpdateLifecycleSeq(seq, r, "stopActivity")) {
        return;
    }
    r.activity.mConfigChangeFlags |= configChanges;

    StopInfo info = new StopInfo();
    performStopActivityInner(r, info, show, true, "handleStopActivity");
    ...
    ...
}   
```

◆ ActivityThread::4041  performStopActivityInner 方法  
```
private void performStopActivityInner(ActivityClientRecord r,
    StopInfo info, boolean keepShown, boolean saveState, String reason) {
    ...
    ...
//4081
    // Next have the activity save its current state and managed dialogs...
    if (!r.activity.mFinished && saveState) {
        if (r.state == null) {
            callCallActivityOnSaveInstanceState(r);
        }
    }
}
```
◆ ActivityThread::4768  callCallActivityOnSaveInstanceState 方法  
```
private void callCallActivityOnSaveInstanceState(ActivityClientRecord r) {
    r.state = new Bundle();
    r.state.setAllowFds(false);
    if (r.isPersistable()) {
        r.persistentState = new PersistableBundle();
        mInstrumentation.callActivityOnSaveInstanceState(r.activity, r.state,
                r.persistentState);
    } else {
        mInstrumentation.callActivityOnSaveInstanceState(r.activity, r.state);
    }
}
```

◆ Instrumentation::1384  callActivityOnSaveInstanceState 方法  
```
public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
    activity.performSaveInstanceState(outState);
}
```

◆ Activity::1494  performSaveInstanceState 方法  
```
final void performSaveInstanceState(Bundle outState) {
    onSaveInstanceState(outState);
    saveManagedDialogs(outState);
    mActivityTransitionState.saveState(outState);
    storeHasCurrentPermissionRequest(outState);
    if (DEBUG_LIFECYCLE) Slog.v(TAG, "onSaveInstanceState " + this + ": " + outState);
}
```