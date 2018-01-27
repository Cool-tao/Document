### HashCode的问题

◆ int 的 hashCode  

```
LogTrack.e("29 = "+Integer.valueOf(29).hashCode());
输出结果就是 29
```

◆ [String 的 hashCode](String_HashCode.md)  
```
LogTrack.e("a = "+"a".hashCode());
输出结果就是 97
LogTrack.e("aa = "+"aa".hashCode());
输出结果就是 3104
```

◆ [boolean 的 hashCode](Boolean_HashCode.md)  
```
LogTrack.e("true = "+Boolean.TRUE.hashCode());
输出结果就是 1231

LogTrack.e("false = "+Boolean.FALSE.hashCode());
输出结果就是 1237

LogTrack.e("true = "+Boolean.valueOf("true").hashCode());
输出结果就是 1231

LogTrack.e("false = "+Boolean.valueOf("false").hashCode());
输出结果就是 1237
```

[apache.HashCodeBuilder自定义Entity的hashCode](Entity_hashCode_Apache.md)  
[IDE自动生成的hashCode](Entity_hashCode_IDE.md)  
