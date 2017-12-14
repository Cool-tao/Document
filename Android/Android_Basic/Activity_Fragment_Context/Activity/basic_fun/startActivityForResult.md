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
 
Intent intent = new Intent(context, IndexActivity.class);  
setResult(Activity.RESULT_OK, intent);   
finish();  

#### 注意  
启动一个新的Activity，可以在它finish的时候，得到想要的 result；    
在当前的Activity的onActivityResult方法回调中得到这些数据，    
如果想要使用startActivityForResult，对应的requestCode必须 >= 0 ；    
