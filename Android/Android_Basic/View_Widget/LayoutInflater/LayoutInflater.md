#### LayoutInflater  

#### ● inflate   
public View inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot) ;  

##### ♫ 假设我翻译的是对的 ♫  
根据xml的资源id，创建一个有层级结构view；  
如果运行时发生error，会抛出 InflateException；  
resource 表示xml布局的资源id；  

root与attachToRoot    
如果attachToRoot是true， root就是 “具有层级结构的view” 的parent节点；  
如果attachToRoot是false， root仅仅会提供LayoutParams给这个被创建出来的view；  

returnValue  
如果 attachToRoot 是true，返回值就是root；  
如果 attachToRoot 是false，返回值就是这个被创建出来的view；  

##### ♫ 验证一下 ♫  

