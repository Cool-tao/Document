#### 属性  

● android:name  
服务类名，注意如果Service与Activity不在同一个包中，在android:name上必须写上Service的全路径  
● android:label  
服务的名字，如果为空，默认显示的服务名为类名  
● android:icon  
服务的图标  
● android:permission  
申明此服务的权限，这意味着只有提供了该权限的应用才能控制或连接此服务  
● android:process  
表示该服务是否运行在另外一个进程，如果设置了此项，那么将会在包名后面加上这段字符串表示另一进程的名字  
● android:enabled  
如果此项设置为 true，那么 Service 将会默认被系统启动，默认值为 false  
● android:exported  
表示该服务是否能够被其他应用程序所控制或连接，默认值为 false  
