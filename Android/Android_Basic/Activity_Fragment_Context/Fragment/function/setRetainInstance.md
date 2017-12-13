#### setRetainInstance  

Fragment具有属性retainInstance，默认值为false。   
当设备旋转时，fragment会随托管activity一起销毁并重建。  

如果retainInstance属性值为false，FragmentManager会立即销毁该fragment实例。   
随后，为适应新的设备配置，新的Activity的新的FragmentManager会创建一个新的fragment及其视图。  

如果retainInstance属性值为true，则该fragment的视图立即被销毁，但fragment本身不会被销毁。   
为适应新的设备配置，当新的Activity创建后，新的FragmentManager会找到被保留的fragment，并重新创建其试图。  

