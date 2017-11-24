> startActivityForResult  

启动一个新的Activity，可以在它finish的时候，得到想要的 result；  
在当前的Activity的onActivityResult方法回调中得到这些数据，  
如果想要使用startActivityForResult，对应的requestCode必须 >= 0 ；  


