#### Bitmap压缩  

##### BitmapFactory.Options.inPreferredConfig;  

Bitmap.Config.RGB_565;  
图片上的每个像素块，占据4个字节，共32位，Alpha，R，G，B四个通道每个通道用8位表示；  
Bitmap.Config.ARGB_4444;  
图片上的每个像素块，占据2个字节，共16位，Alpha，R，G，B四个通道每个通道用4位表示；
Bitmap.Config.RGB_565;  
图片上的每个像素块，占据2个字节，共16位，只有RGB，两个字节中高5位表示红色通道，中间6位表示绿色通道，低5位表示蓝色通道；  
Bitmap.Config.ALPHA_8;  
图片上的每个像素块，占据1个字节，共8位，该字节存储的是图片8位的透明度值；  
 

##### BitmapFactory.Options.inPurgeable  
设置为true时，表示系统内存不足时可以被回 收；  
设置为false时，表示不能被回收。  

##### BitmapFactory.Options.inJustDecodeBounds  
inJustDecodeBounds = true  只加载图片，获取宽高等信息，并不展示图片；  
inJustDecodeBounds = false 展示图片；    

##### BitmapFactory.Options.inSampleSize   
inSampleSize = 2; 就是 宽度只用1/2，高度只用 1/2，就是压缩展示， 占据内存会变成原来的 1/4;  


