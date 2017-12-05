##### onCreateView  

```
private View rootView;//缓存Fragment view
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
{
	/*防止View 重造，控件再次初始化*/
	if(rootView==null){
		rootView=inflater.inflate(R.layout.tab_fragment, null);
		//initView();
	}
	/*过滤Fragment重叠*/
	ViewGroup parent = (ViewGroup) rootView.getParent();
	if (parent != null) {
		parent.removeView(rootView);
	} 
	return rootView;
}

@Override
public void onDestroyView()
{
	super.onDestroyView();
	if(rootView!=null && rootView.getParent()!=null){
		((ViewGroup)rootView.getParent()).removeView(rootView);
	}
}
```