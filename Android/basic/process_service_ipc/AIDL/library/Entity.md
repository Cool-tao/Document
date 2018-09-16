#### DownloadMessageEntity  

● readFromParcel  
一定要手动写一个 readFromParcel 方法；  
注意，此处的读值顺序应当是和writeToParcel()方法中一致的；  
```
fun readFromParcel(inParcel: Parcel) {
    time = inParcel.readString()
    title = inParcel.readString()
    content = inParcel.readString()
}
```

剩下的，用插件自动生成  
```
@SuppressLint("ParcelCreator")
class DownloadMessageEntity(
        var time: String = "2017-12-10 20:00",
        var title: String = "标题",
        var content: String = "具体内容"
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    fun readFromParcel(inParcel: Parcel) {
        time = inParcel.readString()
        title = inParcel.readString()
        content = inParcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(time)
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DownloadMessageEntity> {
        override fun createFromParcel(parcel: Parcel): DownloadMessageEntity {
            return DownloadMessageEntity(parcel)
        }

        override fun newArray(size: Int): Array<DownloadMessageEntity?> {
            return arrayOfNulls(size)
        }
    }
}
```