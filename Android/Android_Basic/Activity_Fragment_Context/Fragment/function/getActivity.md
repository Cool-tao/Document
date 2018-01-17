### getActivity()空指针问题  

◆ 问题描述  
参见 Api21 FragmentManagerImpl源码，moveToState方法中，
只要经历过onDetach ，那么 f.mActivity 就一定被 置空;  


◑ FragmentManagerImpl#moveToState  
```
void moveToState(Fragment f, int newState, int transit, int transitionStyle, boolean keepActive) {
    switch (f.mState) {
        case Fragment.CREATED:
                f.mCalled = false;
                f.onDetach();
                // ... 1020  
                //  在 onDetach 将 activity 置空 
                if (!keepActive) {
                    if (!f.mRetaining) {
                        makeInactive(f);
                    } else {
                        f.mActivity = null;
                        f.mParentFragment = null;
                        f.mFragmentManager = null;
                        f.mChildFragmentManager = null;
                    }
                }
            }
	f.mState = newState;
}
```
◆ 解决办法  
声明全局变量 mActivity，在onAttach方法中给mActivity初始化；  

◆ 参考  
https://www.jianshu.com/p/180d2cc0feb5