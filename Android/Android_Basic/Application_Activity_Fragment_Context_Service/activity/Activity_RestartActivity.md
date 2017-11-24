###### 重启当前Activity
```
Intent intent = getIntent();
intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
overridePendingTransition(0, 0);
finish();
startActivity(intent);
overridePendingTransition(0, 0);
```