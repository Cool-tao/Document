###### App假装进程包活
```
/*我能做到的，仅仅是让App存活的稍微久一点，仅此而已*/
/*android:alwaysRetainTaskState="true" 只在 入口在 Activity 有效*/
<activity
	android:name=".ui.MainActivity"
	android:alwaysRetainTaskState="true"
    android:clearTaskOnLaunch="false"
	android:theme="@style/alex_theme_cold_start"
	>
	<intent-filter>
		<action android:name="android.intent.action.MAIN"/>

		<category android:name="android.intent.category.LAUNCHER"/>
	</intent-filter>
</activity>
		
public class MainActivity extends BaseActivity{
    @Override
    public void onBackPressed()
    {
		/*写在主页 ， 按返回键返回桌面，不结束Activity*/
        moveTaskToBack(true);
    }
}
```