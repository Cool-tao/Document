### setRetainInstance  

Fragment具有属性retainInstance，默认值为false。   
当设备旋转时，fragment会随托管activity一起销毁并重建。  

如果retainInstance属性值为false，FragmentManager会立即销毁该fragment实例。   
随后，为适应新的设备配置，新的Activity的新的FragmentManager会创建一个新的fragment及其视图。  

如果retainInstance属性值为true，则该fragment的视图立即被销毁，但fragment本身不会被销毁。   
为适应新的设备配置，当新的Activity创建后，新的FragmentManager会找到被保留的fragment，并重新创建其视图；  
一旦发生Activity重启现象，Fragment会跳过onDestroy直接进行onDetach（界面消失、对象还在），  
而Fragment重启时也会跳过onCreate，而onAttach和onActivityCreated还是会被调用。  
需要注意的是，要使用这种操作的Fragment不能加入backstack后退栈中。  
并且，被保存的Fragment实例不会保持太久，若长时间没有容器承载它，也会被系统回收掉的。  

