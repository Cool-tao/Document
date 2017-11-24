###### 系统Intent介绍
```
回到Home窗口
Intent intent = new Intent("android.intent.action.MAIN");
intent.addCategory("android.intent.category.HOME");
显示系统设置主界面
Intent intent = new Intent("android.settings.SETTINGS");
设置无线网：
Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
浏览网页
Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
直接拨打拨打电话:
Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:12345678"));
将电话号码传入拨号App   
Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:12345678"));
调用拨号App
Intent intent = new Intent("com.android.phone.action.TOUCH_DIALER");
查看联系人
Intent intent = new Intent("com.android.contacts.action.LIST_CONTACTS");
编辑短信＋发送SMS/MMS
Intent intent = new Intent(Intent.ACTION_VIEW);
　intent.putExtra("sms_body", "The SMS text");
intent.setType("vnd.android-dir/mms-sms");
startActivintenty(intent);

发送短信 
Uri uri = Uri.parse("smsto:0800000123");
Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
intent.putExtra("sms_body", "The SMS text");
startActivintenty(intent);
发送彩信 Uri uri = Uri.parse("content://media/external/images/media/23");
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra("sms_body", "some text");
intent.putExtra(Intent.EXTRA_STREAM, uri);
intent.setType("image/png");
startActivintenty(intent);
发送Email
Uri uri = Uri.parse("mailto:xxx@abc.com");
Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
　startActivintenty(intent);
Intent intent = new Intent(Intent.ACTION_SEND);
　intent.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
intent.putExtra(Intent.EXTRA_TEXT, "The email body text");
intent.setType("text/plain");
startActivintenty(Intent.createChooser(intent, "Choose Email Client"));
Intent intent=new Intent(Intent.ACTION_SEND);
String[] tos={"me@abc.com"};
String[] ccs={"you@abc.com"};
intent.putExtra(Intent.EXTRA_EMAIL, tos);
intent.putExtra(Intent.EXTRA_CC, ccs);
intent.putExtra(Intent.EXTRA_TEXT, "The email body text");
intent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
intent.setType("message/rfc822");
startActivintenty(Intent.createChooser(intent, "Choose Email Client"));
添加附件 
Intent intent = new Intent(Intent.ACTION_SEND);
intent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
intent.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
sendIntent.setType("audio/mp3");
startActivintenty(Intent.createChooser(intent, "Choose Email Client"));
播放多媒体
Intent intent = new Intent(Intent.ACTION_VIEW);
Uri uri = Uri.parse("file:///sdcard/song.mp3");
intent.setDataAndType(uri, "audio/mp3");
startActivintenty(intent);
Uri uri = Uri.wintenthAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
Intent intent = new Intent(Intent.ACTION_VIEW, uri);
startActivintenty(intent);

启动处理音频的程序
Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
audioIntent.setType("audio/*");
startActivity(Intent.createChooser(audioIntent, "选择音频程序"));

卸载程序 
Uri uri = Uri.fromParts("package", strPackageName, null);
Intent intent = new Intent(Intent.ACTION_DELETE, uri);
startActivintenty(intent);
```