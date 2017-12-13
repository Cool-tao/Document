#### JobService  

<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>  

```
<service
    android:name=".huggles.LollipopDownloadJobService"
    android:enabled="true"
    android:exported="true"
    android:permission="android.permission.BIND_JOB_SERVICE">
    <intent-filter>
        <action android:name="com.alex.andfun.service.huggles.LollipopDownloadJobService"/>
    </intent-filter>
</service>
```  

```
if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
    IntentUtil.startService(C.ServiceAction.LOLLIPOP_DOWNLOAD)
}
```  


