###### UserEntity 的hashCode 怎么自定义


> 利用  org.apache.commons.lang3.builder.HashCodeBuilder() 插件 生成 hashCode

```
public class UserEntity {
    private int age;
    private String name;
    private Integer height;

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder()
                .append(age)
                .append(name)
                .append(height)
                .toHashCode();
    }
}
```

> HashCodeBuilder

```
public class HashCodeBuilder implements Builder<Integer> {
    public HashCodeBuilder() {
        iConstant = 37;
        iTotal = 17;
    }
    
    public HashCodeBuilder append(final int value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }
    
    public HashCodeBuilder append(final Object object) {
        if (object == null) {
            iTotal = iTotal * iConstant;

        } else {
            if (object.getClass().isArray()) {
                // factor out array case in order to keep method small enough
                // to be inlined
                appendArray(object);
            } else {
                iTotal = iTotal * iConstant + object.hashCode();
            }
        }
        return this;
    }
    
    public int toHashCode() {
        return iTotal;
    }
    
    
}    
```