###### startActivityForResult

> 启动者
```
private static final int requestCodeLoginOK = 100;

Intent intent = new Intent(context, LoginActivity.class);
startActivityForResult(intent, requestCodeLoginOK);

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data)
{
	super.onActivityResult(requestCode, resultCode, data);
	if(data == null){
		return;
	}
	if(requestCode == requestCodeVersion){
			
	}
}
```

> 启动项

```
Intent intent = new Intent(context, IndexActivity.class);
setResult(Activity.RESULT_OK, intent); 
finish();
```
