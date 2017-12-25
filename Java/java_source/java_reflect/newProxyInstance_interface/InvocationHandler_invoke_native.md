### InvocationHandler.invoke触发机制  

◆ Proxy.newProxyInstance  
```
public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h) throws IllegalArgumentException{
    Objects.requireNonNull(h);
    // 这里会生成 代理类  
    Class<?> cl = getProxyClass0(loader, intfs);
    ... 
    try {
        final Constructor<?> cons = cl.getConstructor(constructorParams);
        ...
        // InvocationHandler 对象，在这里被用到， 断点进去  
        return cons.newInstance(new Object[]{h});
    } catch (IllegalAccessException|InstantiationException e) {
        throw new InternalError(e.toString(), e);
    } 
}
```
◆ Constructor.newInstance  
```
public T newInstance(Object ... initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    ... 
    ConstructorAccessor ca = constructorAccessor;   // read volatile
    if (ca == null) {
        // 这里 可以 看到 ca 就是 NativeConstructorAccessorImpl  
        ca = acquireConstructorAccessor();
    }
    // InvocationHandler 对象，在这里被用到， 断点进去  
    T inst = (T) ca.newInstance(initargs);
    return inst;
}
```
◆ sun.reflect.NativeConstructorAccessorImpl.newInstance  
```
public Object newInstance(Object[] var1) throws InstantiationException, IllegalArgumentException, InvocationTargetException {
    if (++this.numInvocations > ReflectionFactory.inflationThreshold() && !ReflectUtil.isVMAnonymousClass(this.c.getDeclaringClass())) {
        ConstructorAccessorImpl var2 = (ConstructorAccessorImpl)(new MethodAccessorGenerator()).generateConstructor(this.c.getDeclaringClass(), this.c.getParameterTypes(), this.c.getExceptionTypes(), this.c.getModifiers());
        this.parent.setDelegate(var2);
    }

    // InvocationHandler 对象，在这里被用到，
    return newInstance0(this.c, var1);
}
```
◆ private static native Object newInstance0(Constructor<?> var0, Object[] var1)    

