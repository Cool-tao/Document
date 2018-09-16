##### 横屏透明的Activity

```
 <activity
            android:name="com.ubisys.ubisyssafety.activity.DialogInfoActivity"
            android:screenOrientation="landscape"
            android:theme="@style/activity_landscape" />
			
//style_activity_landscape.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="activity_landscape" parent="@android:Theme.Dialog">
        <item name="android:windowFrame">@null</item>
        <item name="android:windowBackground">@drawable/shape_activity_landscape</item>
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowContentOverlay">@null</item>
    </style>
</resources>
//shape_activity_landscape.xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle" >
    <solid android:color="#009ACD" />
    <corners android:radius="5dip" />
</shape>
```