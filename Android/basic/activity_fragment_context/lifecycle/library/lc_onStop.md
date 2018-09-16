### onStop
结论  
执行onStop之前一定会 执行 onSaveInstanceState  

◆ 为什么 会执行 onStop  
◆ ActivityThread::1584  handleMessage 方法  
```
public void handleMessage(Message msg) {
    ..
    ..
//1618
    case STOP_ACTIVITY_SHOW: {
        Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStop");
        SomeArgs args = (SomeArgs) msg.obj;
        handleStopActivity((IBinder) args.arg1, true, args.argi2, args.argi3);
        Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
    }
}
```
◆ ActivityThread::4132  handleStopActivity 方法  
```
private void handleStopActivity(IBinder token, boolean show, int configChanges, int seq) {
    ActivityClientRecord r = mActivities.get(token);
    if (!checkAndUpdateLifecycleSeq(seq, r, "stopActivity")) {
        return;
    }
    r.activity.mConfigChangeFlags |= configChanges;

    StopInfo info = new StopInfo();
// 4140
    /*这时候 会 关联   activy.onSaveInstanceState  方法  */
    performStopActivityInner(r, info, show, true, "handleStopActivity");
}
```
◆ ActivityThread::4041  performStopActivityInner 方法  
```
private void performStopActivityInner(ActivityClientRecord r,
        StopInfo info, boolean keepShown, boolean saveState, String reason) {
        ...
        ...
        // Next have the activity save its current state and managed dialogs...
        if (!r.activity.mFinished && saveState) {
            if (r.state == null) {
// 4081            
                // activity 会执行 onSaveInstanceState
                callCallActivityOnSaveInstanceState(r);
            }
        }
        if (!keepShown) {
            try {
// 4088              
                // Now we are idle.
                r.activity.performStop(false /*preserveWindow*/);
            } catch (Exception e) {
                
            }
        }
    }
}
```