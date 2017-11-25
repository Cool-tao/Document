#### Bitmap压缩  

##### BitmapFactory.Options.inJustDecodeBounds  
inJustDecodeBounds = true  只加载图片，获取宽高等信息，并不展示图片；  
inJustDecodeBounds = false 展示图片；    


##### BitmapFactory.Options.inSampleSize   
inSampleSize = 2; 就是 宽度只用1/2，高度只用 1/2，就是压缩展示， 占据内存会变成原来的 1/4;  


