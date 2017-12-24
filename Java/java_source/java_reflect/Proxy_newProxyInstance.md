### Proxy.newProxyInstance  

◆ 动态代理接口  
假设为一个接口做动态代理，因为接口是比较特殊的，没有构造方法；  

```
interface ApiService{

    @FormUrlEncoded
    @POST("login")
    fun login(@FieldMap params: Map<String, String>): Observable<WrapperBean<UserBean>> 
    
}
```

◆ Proxy.newProxyInstance  
```
@CallerSensitive
public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) throws IllegalArgumentException
{
    Objects.requireNonNull(h);
    // intfs 只有一个元素， name = com.alex.network.ApiService  
    final Class<?>[] intfs = interfaces.clone();
    ...
    try {
        ...
        // 调用 java.lang.reflect.Constructor.newInstance  为 接口产生  实例化对象  
        return cons.newInstance(new Object[]{h});
    } catch (IllegalAccessException|InstantiationException e) {
    }
    ... 
}
```
◆ Proxy.getProxyClass0  
```
private static Class<?> getProxyClass0(ClassLoader loader,Class<?>... interfaces) {
    ...
    return proxyClassCache.get(loader, interfaces);
}
```
◆ WeakCache.get  
```
public V get(K key, P parameter) {
    ...
    //subKeyFactory.apply(key, parameter)  生成代理对象  
    Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
    ...
}
```
◆  Proxy.KeyFactory.apply  
```
@Override
public Object apply(ClassLoader classLoader, Class<?>[] interfaces) {
    switch (interfaces.length) {
        // 这个代理对象 就是 java.lang.reflect.Proxy.Key1 类对象 
        case 1: return new Key1(interfaces[0]); // the most frequent
        ...
    }
}
```
