###### HashMap、Hashtable、HashSet 和 ConcurrentHashMap 的比较

> HashMap
- 键值对，一组key、value元素
- key、value 都可以为空（允许多个不同的key，value为空；）；
- key相同，value会被覆盖；

> Hashtable
- 键值对，一组key、value元素
- key 和 value，均不可以为空（ hashtable.put(null,"AA");     hashtable.put("AA",null);  都会抛异常）

> HashSet
- 不是键值对，只有一个元素
- 允许放入 null
- 放入重复元素，会被覆盖

> ConcurrentHashMap
- 键值对，一组key、value元素
- key 和 value，均不可以为空（ hashtable.put(null,"AA");     hashtable.put("AA",null);  都会抛异常）



