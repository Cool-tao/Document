### Class类文件结构  
Class文件是一组，以8位（字节）为基础单位的二进制流，各个数据项目严格按照顺序紧凑地排列在Class文件之中，中间没有添加任何分隔符，  
这使得整个Class文件中存储的内容几乎全部是程序运行的必要数据，没有空隙存在。 当遇到需要占用8位字节以上空间的数据项时，  
则会按照高位在前的方式分割成若干个8位字节进行存储。  
根据Java虚拟机规范的规定，Class文件格式采用一种类似于C语言结构体的伪结构来存储数据，这种伪结构中只有两种数据类型：无符号数和表；  
[魔数与Class文件版本](CFS_MagicNumber_Version.md)    
[常量池](CFS_constant_pool.md)  
[访问标志](CFS_access_flag.md)  
[类索引、 父类索引与接口索引集合](CFS_SuperList.md)  
[字段表集合](CFS_FieldInfo.md)  
[方法表集合](CFS_MethodInfo.md)  


