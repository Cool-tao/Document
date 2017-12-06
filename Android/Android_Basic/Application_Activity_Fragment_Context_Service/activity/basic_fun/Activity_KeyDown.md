###### 物理按键相关
> 点击 手机返回键，切换后台，不是finish
```
@Override
public void onBackPressed()
{
	//实现Home键效果 
	//super.onBackPressed();这句话一定要注掉,不然又去调用默认的back处理方式了 
	Intent intent= new Intent(Intent.ACTION_MAIN);
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	intent.addCategory(Intent.CATEGORY_HOME);
	startActivity(intent);
}
```
> 捕获菜单键
```
@Override
public boolean onKeyDown(int keyCode, KeyEvent event)
{
	if(keyCode==KeyEvent.KEYCODE_MENU){
		dl.openDrawer(right);	
	}
	return super.onKeyDown(keyCode, event);
}
```
> 屏蔽返回键 自定义返回键
```
/**最好用这个 方式，防止上一个activity也finish掉，并注释掉 super 防止系统先响应*/
@Override
public void onBackPressed()
{
	//super.onBackPressed();
	startHomeOrBuyActivity();
}
```

> 屏蔽返回键
```
@Override
public boolean onKeyDown(int keyCode, KeyEvent event)
{
	if(keyCode==KeyEvent.KEYCODE_BACK ){

		return true;
	}
	return super.onKeyDown(keyCode, event);
}
```