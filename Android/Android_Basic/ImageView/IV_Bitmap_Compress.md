#### Bitmap压缩  

##### BitmapFactory.Options.inPreferredConfig;  

Bitmap.Config.RGB_ARGB_8888;  
图片上的每个像素块，A, R, G, B 四个通道，每个通道用8位表示，共32位；  
Bitmap.Config.ARGB_4444;  
图片上的每个像素块，A, R, G, B 四个通道，每个通道用4位表示，共16位；
Bitmap.Config.RGB_565;  
图片上的每个像素块，R, G, B 三个通道，两个字节中高5位表示红色通道，中间6位表示绿色通道，低5位表示蓝色通道，共16位；  
Bitmap.Config.ALPHA_8;  
图片上的每个像素块，Alpha 一个通道，存储的是图片8位的透明度值，共8位；  

#### Bitmap所占用字节大小  
图片高度 * 图片宽度 * 一个像素占用的内存大小;  
 

##### BitmapFactory.Options.inPurgeable  
设置为true时，表示系统内存不足时可以被回 收；  
设置为false时，表示不能被回收。  

##### BitmapFactory.Options.inJustDecodeBounds  
inJustDecodeBounds = true  只加载图片，获取宽高等信息，并不展示图片；  
inJustDecodeBounds = false 展示图片；    

##### BitmapFactory.Options.inSampleSize   
inSampleSize = 2; 就是 宽度只用1/2，高度只用 1/2，就是压缩展示， 占据内存会变成原来的 1/4;  


