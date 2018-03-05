### 克隆  

基本数据类型，String默认实现深克隆；  
浅克隆，只是给原始引用数据多了一份指针，多了一个对象的引用，否则要 实现Cloneable接口，重写clone方法；  


其他引用数据类型，需要手动实现 深克隆；  
引用数据类型，深克隆后，会出现2个对象，修改新的对象数值，对原始对象没有任何影响，  
Java 默认基础数据类型，实现深克隆，String类型，也是实现深克隆，  但是其他引用数据类型都是浅克隆，  
如果要对引用数据类型做深克隆，需要手动实现，  

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