### 克隆  
基本数据类型，String默认实现深克隆；  
其他引用数据类型，需要手动实现 深克隆；  
◆ 浅克隆   
```
public class ShallowWordDocument implements Cloneable {
    private String text;
    private String author;
    private List<String> urlList = new ArrayList<>();
    public ShallowWordDocument cloneDocument() {
        try {
            ShallowWordDocument document = (ShallowWordDocument) super.clone();
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```
◆ 深克隆   
```
public class DeepWordDocument implements Cloneable {
    private String text;
    private String author;
    private ArrayList<String> urlList = new ArrayList<>();
    public DeepWordDocument cloneDocument() {
        try {
            DeepWordDocument document = (DeepWordDocument) super.clone();
            document.urlList = (ArrayList<String>) this.urlList.clone();
            return document;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
```