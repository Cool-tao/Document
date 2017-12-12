#### 属性  

● android:name  
服务全类名；  
● android:label  
服务的名字，如果为空，默认显示的服务名为类名  
● android:icon  
服务的图标  
● android:permission  
申明此服务的权限，这意味着只有提供了该权限的应用才能控制或连接此服务  
● android:process  
默认为空，表示服务在当前进程，即主进程运行；  
自定义时，表示服务在新的进程运行，所以 运行时 传递数据，需要AIDL；  
android:process=":name"  一般用:remote表示远程服务；  
● android:enabled  
如果此项设置为 true，那么 Service 将会默认被系统启动，默认值为 false  
● android:exported  
代表是否能被隐式调用，需要配合intent-filter使用  
● intent-filter    
如果服务允许
```
<intent-filter>
    <action android:name="com.alex.andfun.service.back.LocalDownloadService" />
</intent-filter>
```  
