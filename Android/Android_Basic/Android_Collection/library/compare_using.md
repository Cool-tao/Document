#### 用法比较  


● ArrayMap（android.support.v4.util.ArrayMap）  
键值对，一组key、value元素；  
key、value 都可以为空（允许多个不同的key，value为空；）；  
key相同，value会被覆盖；  
迭代器迭代，迭代时，按照key的hashCode顺序；  

● SimpleArrayMap（android.support.v4.util.SimpleArrayMap）  
键值对，一组key、value元素；  
key、value 都可以为空（允许多个不同的key，value为空；）；  
key相同，value会被覆盖；  
下标索引迭代，迭代时，按照key的hashCode顺序；  

● ArraySet（android.support.v4.util.ArraySet）  
不是键值对，只有一个元素；  
允许放入 null；  
放入重复元素，会被覆盖；  
迭代器迭代，迭代时，按照hashCode顺序；  

● SparseArray（android.util.SparseArray）  
键值对，一组key、value元素；  
key规定必须是int类型；  
value允许放入 null；  
放入重复元素，会被覆盖；  
下标索引迭代，迭代时，按照hashCode顺序；  

● SparseBooleanArray（android.util.SparseBooleanArray）   
● SparseIntArray（android.util.SparseIntArray）   
● SparseLongArray（android.util.SparseLongArray）   
键值对，一组key、value元素；  
key规定必须是int类型；  
value只能放入 boolean，而不是 Boolean；  
放入重复元素，会被覆盖；  
下标索引迭代，迭代时，按照hashCode顺序；  

● LongSparseArray（android.support.v4.util.LongSparseArray）  
键值对，一组key、value元素；  
key规定必须是long类型；  
value允许放入 null；  
放入重复元素，会被覆盖；  
下标索引迭代，迭代时，按照hashCode顺序；  



