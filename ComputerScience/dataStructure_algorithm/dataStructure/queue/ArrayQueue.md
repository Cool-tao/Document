### 顺序队列

简单的顺序队列，用数组存储数据，
```
[_, _, _, _, _]  front、rear 都在最左边（队首）
[A, _, _, _, _]  front 在 index=0，rear在index= 1
[A, B, _, _, _]  front 在 index=0，rear在index= 2
[A, B, C, _, _]  front 在 index=0，rear在index= 3
[A, B, C, D, _]  front 在 index=0，rear在index= 4
[A, B, C, D, E]  front 在 index=0，rear在index= 5（队满）
[_, B, C, D, E]  front 在 index=1，rear在index= 5（队满）
[_, _, C, D, E]  front 在 index=2，rear在index= 5（队满）
[_, _, _, D, E]  front 在 index=3，rear在index= 5（队满）
[_, _, _, _, E]  front 在 index=4，rear在index= 5（队满）
[_, _, _, _, _]  front 在 index=5，rear在index= 5（队满）
这时候的队满，其实就是假溢出；
当
```