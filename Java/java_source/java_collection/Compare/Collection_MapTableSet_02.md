> HashMap

```
public class HashMap<K,V> extends 
    AbstractMap<K,V> implements 
    Map<K,V>, Cloneable, Serializable {
    
}

public abstract class AbstractMap<K,V> implements Map<K,V> {

}
```

> Hashtable
```
public class Hashtable<K,V> extends 
    Dictionary<K,V> implements 
    Map<K,V>, Cloneable, java.io.Serializable {

}

public abstract class Dictionary<K,V> {

}
```
> HashSet
```
public class HashSet<E> extends 
    AbstractSet<E> implements 
    Set<E>, Cloneable, java.io.Serializable{

}

public abstract class AbstractSet<E> extends 
    AbstractCollection<E> implements 
    Set<E> {

}

public abstract class AbstractCollection<E> implements Collection<E> {

}
```