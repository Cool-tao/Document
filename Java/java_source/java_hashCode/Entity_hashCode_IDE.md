### IDE 生成的 hashCode  
事实上 Float.floatToIntBits = Float.hashCode  
◆ 参考Effective Java  
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
假设字段是 tmpKey      
◑ int 类型： tmpKey  
◑ boolean 类型： tmpKey? 1 : 0  
◑ byte 、 char 、 short 类型： (int) tmpKey  
◑ long 类型： (int) (tmpKey ^ tmp >>>32)  
◑ float 类型： Float.floatToInBits(tmpKey)  
◑ double 类型： Double.doubleToLongBits(tmpKey)  
◑ 如果 引用类型： tmpKey==null ? 0 : tmpKey.HashCode()   

