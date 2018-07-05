###### String的hashCode

> hashCode 的源码
```
public int hashCode() {
    int h = hash;
    if (h == 0 && value.length > 0) {
        char val[] = value;

        for (int i = 0; i < value.length; i++) {
            h = 31 * h + val[i];
        }
        hash = h;
    }
    return h;
}
```

- 那么hash的数值是多少？
```
/** Cache the hash code for the string */
private int hash; // Default to 0
```

> 所以 "a" 的hashCode就是 97

> 所以"aa" 的hashCode就是 97+31*97 = 3104


参考  
https://www.cnblogs.com/skywang12345/p/3324958.html  
