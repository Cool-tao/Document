#### 对Context的认识  

Context是一个抽象类，类继承结构图：  
```
Context;    
    ContextImpl;  
    ContextWrapper;  
        Application;  
        Service;  
        ContextThemeWrapper;  
            Activity;  
```

#### 在 Activity、Service中   

getApplication();  
getApplicationContext();  
getBaseContext();  
其中，getApplication  与 getApplicationContext 是同一个对象，hashCode结果是一样的；   

#### 在Fragment中  

getContext();  
getActivity();  

#### 在ContentProvider中

getContext();  

#### 在BroadcastReceiver中
```
public void onReceive(Context context, Intent intent) {
    context.getApplicationContext();
}
```
 





