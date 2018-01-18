### getActivity()空指针问题  

◆ 搞清楚，为什么f.mActivity() 为空    
要搞清楚这个问题，就必须搞清楚下述3个问题，参见 Api21 FragmentManagerImpl源码  
◑ f.mActivity 在哪里赋值？  
◑ f.mActivity 在哪里置空？  
◑ 内存重启之后，f.mActivity 还会被赋值吗？  
◆ f.mActivity 在哪里赋值？  
```
void moveToState(Fragment f, int newState, int transit, int transitionStyle, boolean keepActive) {
	if (f.mState < newState) {  /*首次进来 或者 内存重启  dispatchCreate() f.mState = 0，newState = 1； */
		switch (f.mState) {
			case Fragment.INITIALIZING:
				if (f.mSavedFragmentState != null) {
					f.mSavedViewState = f.mSavedFragmentState.getSparseParcelableArray(FragmentManagerImpl.VIEW_STATE_TAG);
					f.mTarget = getFragment(f.mSavedFragmentState,FragmentManagerImpl.TARGET_STATE_TAG);
					if (f.mTarget != null) {
						f.mTargetRequestCode = f.mSavedFragmentState.getInt(FragmentManagerImpl.TARGET_REQUEST_CODE_STATE_TAG, 0);
					}
					f.mUserVisibleHint = f.mSavedFragmentState.getBoolean(FragmentManagerImpl.USER_VISIBLE_HINT_TAG, true);
					if (!f.mUserVisibleHint) {
						f.mDeferStart = true;
						if (newState > Fragment.STOPPED) {
							newState = Fragment.STOPPED;
						}
					}
				}
				/*这个时候，都会给 fragment 的mActivity赋值， 看似天衣无缝！！！  但是， 还是有问题，稍后分析  问题标号 A0 */
				f.mActivity = mActivity;
				f.mParentFragment = mParent;
				f.mFragmentManager = mParent != null ? mParent.mChildFragmentManager : mActivity.mFragments;
				f.mCalled = false;
				f.onAttach(mActivity);
		}
	}
	f.mState = newState;
}
```
◑ 需要解释 问题标号 A0  
◆ f.mActivity 在哪里置空？  
```
void moveToState(Fragment f, int newState, int transit, int transitionStyle, boolean keepActive) {
	
	if (f.mState < newState) {
	
	} else if (f.mState > newState) {
		switch (f.mState) { /*被回收时 dispatchDestroy() f.mState = 1，newState = 0；  */
			case Fragment.CREATED:
				if (newState < Fragment.CREATED) {
					if (f.mAnimatingAway != null) {
						f.mStateAfterAnimating = newState;
						newState = Fragment.CREATED;
					} else {
						if (!f.mRetaining) {
							f.performDestroy();
						}
						f.mCalled = false;
						f.onDetach();
						if (!keepActive) {
							if (!f.mRetaining) {
								makeInactive(f);
							} else {
								/*这个时候 f.mActivity 会被置为null*/
								f.mActivity = null;
								f.mParentFragment = null;
								f.mFragmentManager = null;
								f.mChildFragmentManager = null;
							}
						}
					}
				}
		}
	}	
	f.mState = newState;
}
```
◆ 内存重启之后，f.mActivity 还会被赋值吗？  
这个问题，等同于 【问题标号 A0  】  
既然，在onCreate 的时候，会执行 f.mActivity = fragmentManagerImpl.mActivity，而且还会执行f.onAttach(fragmentManagerImpl.mActivity)，那么f.getActivity就不可能为null！！  
但是！！！  
◑ fragmentManagerImpl.mActivity，我们先看这个东西的 read 和 write；  
这个mActivity只有一个地方会赋值，那就是 FragmentManagerImpl#attachActivity；  
我们假设，每次activity走onCreate的时候，都会调用FragmentManagerImpl#attachActivity,就不会出现 f.mActivity为空的现象了；  
```
final void attach(Context context, ActivityThread aThread,
        Instrumentation instr, IBinder token, int ident,
        Application application, Intent intent, ActivityInfo info,
        CharSequence title, Activity parent, String id,
        NonConfigurationInstances lastNonConfigurationInstances,
        Configuration config, IVoiceInteractor voiceInteractor) {
    attachBaseContext(context);
    mFragments.attachActivity(this, mContainer, null);
}
```
事实上，只有 在 activity.attach ，这个方法会调用 fragmentManagerImpl.attachActivity；  
那么，什么时候会触发 activity.attach 方法？？了解 startActivity 相关源码，都知道，在 ActivityThread#performLaunchActivity 这个方法，  
在这个 方法里，会反射造一个Activity对象， 让其执行activity.attach方法，之后执行 正常的onCreate等生命周期方法；  
◑ 再看 fragment.onAttach() 究竟干了些 什么？  
```
public void onAttach(Activity activity) {
    mCalled = true;
}
```
没了！！！真的很恐怖，onAttach方法竟然 什么都没干，只是把一个可能为空的mActivity传给fragment 真的好尴尬啊。。。  

◆ 解决办法  
声明全局变量 mActivity，在onAttach方法中给mActivity初始化，而且不要回收TA，就让TA尽情的内存泄露吧；  


