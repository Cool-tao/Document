###### 选择排序 算法

```
两层 for 循环

for  i in 0.. array.size-1
    for j in i.. array.size-1
```

> 基本算法思路

```
i = 0，通过 for j 先找到数组中最大的元素的 下标 x ， 让 array[0] 和 array[x] 交换元素， i++；
i = 1， 通过 for j 找到 剩下的 未排序的 素组中， 最大的元素的 下标 x ，让 array[1] 和 array[x] 交换元素， i++；
...
i = array.size -1
```

> [kotlin实现](SelectionSortTest.kt)