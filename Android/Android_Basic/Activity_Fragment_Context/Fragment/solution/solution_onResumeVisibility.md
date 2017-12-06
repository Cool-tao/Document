##### onResumeVisibility  
假设 NewsActivity包含4个NewsFragment；  
在第0个Fragment展示的时候，只有第0个做数据加载；  
在第0个切换到，第1个的时候，只有第1个做数据加载；    
在第0个展示的时候，点击按钮打开新页面NewsDetailActivity，再回到NewsActivity的时候，只有第0个做数据加载；  
在第0个展示的时候，Home键，回到Launcher，在点击图标或者任务管理器，回到NewsActivity，只有第0个做数据加载；    
在第0个展示的时候，按下电源键，之后在点亮屏幕，回到NewsActivity，只有第0个做数据加载；    

● 如果是fragment 结合layout；  
● 如果是fragment 结合ViewPager；  
都用这一个回调函数，就搞定了；  
```
override fun onResumeVisibility(isVisible: Boolean) {
    LogTrack.w("position = $position  isVisible = $isVisible ")
}
```

#### BaseFragment

```
@SuppressLint("InflateParams")
abstract class BaseFragment : Fragment() {
    protected var rootView: View? = null
    abstract fun getLayoutId(): Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null)
        }
        /*过滤Fragment重叠*/
        rootView?.apply {
            parent?.also {
                val parentView = it as ViewGroup
                parentView.removeView(rootView);
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateData(savedInstanceState)
    }

    abstract fun onCreateData(bundle: Bundle?)

    /**@param isVisible 当前处于展示中*/
    abstract fun onResumeVisibility(isVisible: Boolean)

    /**Fragment-ViewPager*/
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //LogTrack.i("position = $position  isAdded = $isAdded  isVisibleToUser = $isVisibleToUser")
        if (isAdded && isVisibleToUser) {
            onResumeVisibility(true)
        }
    }

    /**Fragment-Layout-show-hidden*/
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (isAdded && !hidden) {
            onResumeVisibility(true)
        }
    }

    @Deprecated(message = "方法过期", replaceWith = ReplaceWith("onResumeVisibility"), level = DeprecationLevel.ERROR)
    override fun onResume() {
        super.onResume()
        if (isAdded && !isHidden && userVisibleHint) {
            onResumeVisibility(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*过滤Fragment重叠*/
        rootView?.apply {
            val parentView = parent as ViewGroup
            parentView.removeView(rootView);
        }
    }
}
```