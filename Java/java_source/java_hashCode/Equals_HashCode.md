###### equals

> apache 的 EqualsBuilder

```
@Override
public boolean equals(Object obj) {
    if (obj == null) {
        return false;
    }
    if (obj == this) {/*比较地址， 如果地址一样了， 对象一定是 相等了*/
        return true;
    }
    if (obj.getClass() != getClass()) {
        return false;
    }
    UserEntity rhs = (UserEntity) obj;
    return new org.apache.commons.lang3.builder.EqualsBuilder()
            .append(this.age, rhs.age)
            .append(this.name, rhs.name)
            .append(this.height, rhs.height)
            .isEquals();
}
```