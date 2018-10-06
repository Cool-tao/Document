### SingleTask 模式 Intent传值

```
@Override
protected void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	getIntentData();
}
@Override  
protected void onNewIntent(Intent intent) {        
	super.onNewIntent(intent);  
	setIntent(intent);  
	getIntentData();
}
private void getIntentData()
{
	String moduleName = getIntent().getStringExtra("moduleName");
	String phoneNum = getIntent().getStringExtra("phoneNum");
	String pwd = getIntent().getStringExtra("pwd");	 
}
```