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

//循环触发，设置任务每三秒定期运行一次  
jobBuilder.setPeriodic(3000);  

//单次定时触发，设置为三秒以后去触发。这是与setPeriodic(long time)不兼容的，并且如果同时使用这两个函数将会导致抛出异常。  
jobBuilder.setMinimumLatency(3000);  

//在约定的时间内设置的条件都没有被触发时三秒以后开始触发。类似于setMinimumLatency(long time)，  
// 这个函数是与 setPeriodic(long time) 互相排斥的，并且如果同时使用这两个函数，将会导致抛出异常。  
jobBuilder.setOverrideDeadline(3000);    

//在设备重新启动后设置的触发条件是否还有效  
jobBuilder.setPersisted(false);  

// 只有在设备处于一种特定的网络状态时，它才触发。  
// JobInfo.NETWORK_TYPE_NONE,无论是否有网络均可触发，这个是默认值；  
// JobInfo.NETWORK_TYPE_ANY，有网络连接时就触发；  
// JobInfo.NETWORK_TYPE_UNMETERED，非蜂窝网络中触发；  
// JobInfo.NETWORK_TYPE_NOT_ROAMING，非漫游网络时才可触发；  
jobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);  


//设置手机充电状态下触发  
jobBuilder.setRequiresCharging(true);  

//设置手机处于空闲状态时触发  
jobBuilder.setRequiresDeviceIdle(true);  

//设置开始安排任务，它将返回一个状态码  
//JobScheduler.RESULT_SUCCESS，成功  
//JobScheduler.RESULT_FAILURE，失败  
```
if (mJobScheduler.schedule(jobInfo) == JobScheduler.RESULT_FAILURE) {
    //安排任务失败  
}
```

//停止指定JobId的工作服务  
mJobScheduler.cancel(JOB_ID);  

//停止全部的工作服务  
mJobScheduler.cancelAll();  

