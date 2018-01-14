### IDE 生成的 hashCode  
事实上 Float.floatToIntBits = Float.hashCode  
```
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + age;
    result = 31 * result + name.hashCode();
    result = 31 * result + (balance != +0.0f ? Float.floatToIntBits(balance) : 0);
    result = 31 * result + (age != 0 ? Integer.hashCode(age) : 0);
    return result;
}
```