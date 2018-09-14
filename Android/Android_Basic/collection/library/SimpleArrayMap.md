#### SimpleArrayMap  

SimpleArrayMap主要有两个数组，mHashes 存放key的hashCode，mArray 依次存放key和value，也就是说，成对的key、value，是存放在相邻的位置的；  
SimpleArrayMap在计算与查找索引位置，使用的是二分法查找，时间复杂度是log2N；  
当数组元素达到容量限制时，会倍增扩容，用System.arraycopy拷贝老数组元素；  
当数据量较小的情况下，事实上安卓平台下，数据量与后端相比较而言，数据量显得微不足道，使用SimpleArrayMap相对来说，是比较节约内存空间的； 
 
