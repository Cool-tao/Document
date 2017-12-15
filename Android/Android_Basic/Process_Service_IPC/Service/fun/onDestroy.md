##### onDestroy  

● 以startService启动service，调用stopService结束时触发此方法；  
● 以bindService启动service，以unbindService结束时触发此方法；  
● 先以startService 启动服务，再用bindService绑定服务，结束时必须先调用unbindService解绑再使用stopService结束service才会触发此方法；  
