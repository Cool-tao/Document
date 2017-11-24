###### boolean 的 hashCode

> hashCode
```
@Override
public int hashCode() {
    return Boolean.hashCode(value);
}

public static int hashCode(boolean value) {
    return value ? 1231 : 1237;
}
```

###### 事实上就是 "true"  和 "false"， 的hashCode值
