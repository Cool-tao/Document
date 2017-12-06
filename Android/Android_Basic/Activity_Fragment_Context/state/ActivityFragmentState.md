#### 生命周期与状态保存

默认 情况下，Activity 和 Fragment 会自动保存 View的状态，前提是View自己实现了 onSaveInstanceState、onRestoreInstanceState；  
Activity会通过 android:id 逐个恢复View的State；  
也就是说，如果 android:id 为空，View将不具备恢复State的能力了；  

然而我们注意到，Fragment只有：onActivityCreated、onSaveInstanceState、onViewStateRestored方法，并没有onRestoreInstanceState方法；  
一旦Fragment从回退栈出来，Fragment本身还在，View却是重新创建的；  
但是给TextView、EditText设置 android:freezeText="true" 会让其在Fragment内，自动保存State；   

#### 结论：  
所有的自定义控件，都应该实现State相关方法，onSaveInstanceState and onRestoreInstanceState；  
所有的控件都要有添加标签 android:id ，否则它将不具有保存State的能力；  


> 参考  

https://inthecheesefactory.com/blog/fragment-state-saving-best-practices/en  
https://github.com/nuuneoi/StatedFragment  


