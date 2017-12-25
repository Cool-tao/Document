#### Reflect  

◆ Proxy.newProxyInstance动态代理接口  
概述就是，利用反射，解class，拿到关于interface的所有方法的声明，  
在内存中生成关于目标接口的代理类，这个代理类所有的方法，在调用之前，都会先调用invokeHandler.invoke方法；  
所以，被造出来的这个代理对象，每调用一个方法，都会触发invokeHandler.invoke的回调；  
[Proxy.newProxyInstance 动态代理接口](newProxyInstance_interface/Proxy_newProxyInstance_interface.md)  
[InvocationHandler.invoke触发机制](newProxyInstance_interface/InvocationHandler_invoke.md)  
[InvocationHandler.invoke触发机制native](newProxyInstance_interface/InvocationHandler_invoke_native.md)  

