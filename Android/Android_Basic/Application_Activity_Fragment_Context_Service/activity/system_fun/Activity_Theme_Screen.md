###### 关于主题与标题等
> 去掉Activity标题
 
requestWindowFeature(Window.FEATURE_NO_TITLE);  
getActionBar().hide();  
 
> 开启全屏
 
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
 
