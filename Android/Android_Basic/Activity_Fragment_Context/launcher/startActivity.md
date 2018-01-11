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
◆ ActivityManagerService.startActivity::startActivityAsUser  
```
@Override
public final int startActivityAsUser(IApplicationThread caller, String callingPackage,
        Intent intent, String resolvedType, IBinder resultTo, String resultWho, int requestCode,
        int startFlags, ProfilerInfo profilerInfo, Bundle options, int userId) {
    enforceNotIsolatedCaller("startActivity");
    userId = handleIncomingUser(Binder.getCallingPid(), Binder.getCallingUid(), userId,false, ALLOW_FULL_ONLY, "startActivity", null);
    return mStackSupervisor.startActivityMayWait(caller, -1, callingPackage, intent,
            resolvedType, null, null, resultTo, resultWho, requestCode, startFlags, profilerInfo, null, null, options, userId, null, null);
}
```
◆ ActivityStackSupervisor#startActivityMayWait  
```
final int startActivityMayWait(IApplicationThread caller, int callingUid,
    String callingPackage, Intent intent, String resolvedType,
    IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
    IBinder resultTo, String resultWho, int requestCode, int startFlags,
    ProfilerInfo profilerInfo, WaitResult outResult, Configuration config,
    Bundle options, int userId, IActivityContainer iContainer, TaskRecord inTask) {

    // ... 927  
    int res = startActivityLocked(caller, intent, resolvedType, aInfo,
            voiceSession, voiceInteractor, resultTo, resultWho,
            requestCode, callingPid, callingUid, callingPackage,
            realCallingPid, realCallingUid, startFlags, options,
            componentSpecified, null, container, inTask);
    
}
```  
◆ ActivityStackSupervisor#startActivityLocked  
```
final int startActivityLocked(IApplicationThread caller,
    Intent intent, String resolvedType, ActivityInfo aInfo,
    IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor,
    IBinder resultTo, String resultWho, int requestCode,
    int callingPid, int callingUid, String callingPackage,
    int realCallingPid, int realCallingUid, int startFlags, Bundle options,
    boolean componentSpecified, ActivityRecord[] outActivity, ActivityContainer container,
    TaskRecord inTask) {
    doPendingActivityLaunchesLocked(false);

    // ... 1489  
    err = startActivityUncheckedLocked(r, sourceRecord, voiceSession, voiceInteractor,startFlags, true, options, inTask);
    // ...
    return err;
    
}
```
◆ ActivityStackSupervisor#startActivityUncheckedLocked  
```
final int startActivityUncheckedLocked(ActivityRecord r, ActivityRecord sourceRecord,
        IVoiceInteractionSession voiceSession, IVoiceInteractor voiceInteractor, int startFlags,
        boolean doResume, Bundle options, TaskRecord inTask) {
    // ... 2175  
    ActivityStack.logStartActivity(EventLogTags.AM_CREATE_ACTIVITY, r, r.task);
    targetStack.mLastPausedActivity = null;
    targetStack.startActivityLocked(r, newTask, doResume, keepCurTransition, options);
    if (!launchTaskBehind) {
        // Don't set focus on an activity that's going to the back.
        mService.setFocusedActivityLocked(r);
    }
    return ActivityManager.START_SUCCESS;
}
```
◆ ActivityStack#startActivityLocked  
```
final void startActivityLocked(ActivityRecord r, boolean newTask,
        boolean doResume, boolean keepCurTransition, Bundle options) {
    TaskRecord rTask = r.task;
    // ... 2118  
    if (doResume) {
        mStackSupervisor.resumeTopActivitiesLocked(this, r, options);
    }
}
```  
◆ ActivityStackSupervisor#resumeTopActivitiesLocked  
```
boolean resumeTopActivitiesLocked(ActivityStack targetStack, ActivityRecord target, Bundle targetOptions) {
    // .. 2423  
    if (targetStack == null) {
        targetStack = getFocusedStack();
    }
    boolean result = false;
    if (isFrontStack(targetStack)) {
        result = targetStack.resumeTopActivityLocked(target, targetOptions);
    }
    for (int displayNdx = mActivityDisplays.size() - 1; displayNdx >= 0; --displayNdx) {
        final ArrayList<ActivityStack> stacks = mActivityDisplays.valueAt(displayNdx).mStacks;
        for (int stackNdx = stacks.size() - 1; stackNdx >= 0; --stackNdx) {
            final ActivityStack stack = stacks.get(stackNdx);
            if (stack == targetStack) {
                continue;
            }
            if (isFrontStack(stack)) {
                stack.resumeTopActivityLocked(null);
            }
        }
    }
    return result;
}
```  
ActivityStack#resumeTopActivityLocked::resumeTopActivityInnerLocked  
```
final boolean resumeTopActivityInnerLocked(ActivityRecord prev, Bundle options) {
    // ... 1642  
    boolean pausing = mStackSupervisor.pauseBackStacks(userLeaving, true, dontWaitForPause);
    if (mResumedActivity != null) {
        pausing |= startPausingLocked(userLeaving, false, true, dontWaitForPause);
        if (DEBUG_STATES) Slog.d(TAG, "resumeTopActivityLocked: Pausing " + mResumedActivity);
    }
}        
```
◆ ActivityStack#startPausingLocked  
```
final boolean startPausingLocked(boolean userLeaving, boolean uiSleeping, boolean resuming,boolean dontWait) {
        
// ... 845        
if (prev.app != null && prev.app.thread != null) {
        if (DEBUG_PAUSE) Slog.v(TAG, "Enqueueing pending pause: " + prev);
        try {
            EventLog.writeEvent(EventLogTags.AM_PAUSE_ACTIVITY,
                    prev.userId, System.identityHashCode(prev),
                    prev.shortComponentName);
            mService.updateUsageStats(prev, false);
            prev.app.thread.schedulePauseActivity(prev.appToken, prev.finishing,
                    userLeaving, prev.configChangeFlags, dontWait);
        } catch (Exception e) {
            // Ignore exception, if process died other code will cleanup.
            Slog.w(TAG, "Exception thrown during pause", e);
            mPausingActivity = null;
            mLastPausedActivity = null;
            mLastNoHistoryActivity = null;
        }
    } else {
        mPausingActivity = null;
        mLastPausedActivity = null;
        mLastNoHistoryActivity = null;
    }
}
```
◆ 参考 
https://github.com/yipianfengye/androidSource/blob/master/14%20activity%E5%90%AF%E5%8A%A8%E6%B5%81%E7%A8%8B.md
