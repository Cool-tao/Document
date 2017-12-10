#### Service与Thread  

Service 与 Thread没有任何关系；  
Service是工作在主线程，Thread工作在子线程；  
Service的运行依赖进程，而非Activity。Thread依赖某个Activity；  
Service的运行使用start、stop， 或者 bind、unbind。Thread的运行使用start和外部控制变量；  


