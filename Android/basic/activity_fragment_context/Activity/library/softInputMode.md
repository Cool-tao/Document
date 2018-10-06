### 关于输入法
> 强制，Activity第一次不打开输入法
```
<activity
		android:name="com.subzero.userman.LoginActivity"
		android:configChanges="orientation|keyboardHidden"
		android:windowSoftInputMode="adjustUnspecified|stateHidden" 
		
		android:launchMode="singleTop"
		android:screenOrientation="portrait"
/>
```
> 强制，Activity第一次打开输入法：只能调用一次，只有一次有效
```
getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
```
> 强制，android 输入法弹出 把布局顶上去：（不让它上去）
```
<activityandroid:name=".activity.HomeActivity"
	android:windowSoftInputMode="adjustPan|stateHidden"
/>
```
> 强制，android 输入法弹出 把布局顶上去：（让它上去）
```
<activity
	android:name="com.subzero.userman.LoginActivity"
	android:launchMode="singleTop"
	android:windowSoftInputMode="adjustResize"
	android:screenOrientation="portrait" />
```
