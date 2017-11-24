###### Glide缓存问题

```
Glide.with(this@BasicPhotoUploadActivity)
        .load(imgUrl)
        .signature(StringSignature(System.currentTimeMillis().toString()))
        .into(this)
```