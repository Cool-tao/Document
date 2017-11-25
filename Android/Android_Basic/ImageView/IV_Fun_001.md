##### setImageURI不刷新   
解决：   
1、使用不同的文件名（不同的URI）   
2、使用setImageBitmap的方式代替   
```
Bitmap bmp;
try {
bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.fromFile(file));
} catch (Exception e) { 

}
```
iv.setImageBitmap(bmp);   
3、先制空，再刷新   
```
fun ImageView.setImageUrl(url: String?) {
    try {
        (url == null).yes { return }
        this.setImageURI(null)
        this.setImageURI(Uri.parse(url))
    } catch (ex: Exception) {
        ex.logW()
    }
}
```