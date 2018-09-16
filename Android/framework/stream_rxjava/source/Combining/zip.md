### zip  
将多个Observable 合并成一个Observable  

◆ 示例 1  
```
Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
    @Override
    public String apply(String left, String right) throws Exception {
        return left + "::" + right;
    }
}).subscribe(new LiteObserver<String>() {
    @Override
    public void onNext(String result) {
        LogTrack.w(result);
    }
});
```  
◆ 示例 2  
```
List<Observable<String>> list = new ArrayList<>();
list.add(observable1);
list.add(observable2);
Observable.zip(list, new Function<Object[], Object>() {
    @Override
    public Object apply(Object[] objects) throws Exception {
        return objects[0] + "::" + objects[1];
    }
}).subscribe(new LiteObserver<Object>() {
    @Override
    public void onNext(Object result) {
        LogTrack.w(result);
    }
});
```