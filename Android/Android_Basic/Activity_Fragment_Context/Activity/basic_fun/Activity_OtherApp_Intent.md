###### 不同App 的 Activity 之间的传值
> A App：
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
	
> B App：
```
Intent intent = new Intent("com.alex.floatindicator.activity.FloatIndicatorActivity", 
Uri.parse("params://111"));
startActivity(intent);
```
