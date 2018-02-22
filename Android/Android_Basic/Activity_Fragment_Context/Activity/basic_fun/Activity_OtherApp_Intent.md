### 不同App 的 Activity 之间的传值  
◆ A App：  
```
<activity
		android:name=".activity.FloatIndicatorActivity"
		android:exported="true"
		android:launchMode="singleTop"
		android:screenOrientation="portrait">
		<intent-filter>
			<action android:name="com.alex.floatindicator.activity.FloatIndicatorActivity" />

			<data android:scheme="params" />

			<category android:name="android.intent.category.DEFAULT" />
		</intent-filter>
</activity>

```
	
◆ B App：  
```
Intent intent = new Intent("com.alex.floatindicator.activity.FloatIndicatorActivity", 
Uri.parse("params://111"));
startActivity(intent);
```  

◆ A App：  
```
<activity android:name=".order.OrderActivity">
    <intent-filter>
        <data
            android:host="@string/android_host"
            android:path="/order"
            android:port="@string/android_port"
            android:scheme="@string/android_scheme" />
        <!-- 一下几行必须设置 -->
        <action android:name="android.intent.action.VIEW" /> <!-- 隐式调用必须声明 -->
        <category android:name="android.intent.category.DEFAULT" /> <!-- 隐式调用必须声明 -->
        <category android:name="android.intent.category.BROWSABLE" /> <!-- BROWSABLE的意思就是浏览器在特定条件下可以打开你的Activity -->
    </intent-filter>
</activity>  
```  
```
<string name="android_scheme">andfun</string>
<string name="android_host">router</string>
<string name="android_port">9527</string>
```
uri =  schema://host:port/path?key0=value0&key1=value1  





