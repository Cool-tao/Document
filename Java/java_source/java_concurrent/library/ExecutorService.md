### ExecutorService  
interface ExecutorService extends Executor  
◆ 关闭线程池  
shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务  
shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务  
◆ invokeAny  
只获取最先完成任务的callable  
```
ExecutorService executorService = Executors.newCachedThreadPool();
List<Callable<String>> list = new ArrayList<>(4);
list.add(new InnerCallable(2000));
list.add(new InnerCallable(3000));
list.add(new InnerCallable(4000));
String invokeAny = executorService.invokeAny(list);
LogTrack.w(invokeAny);
executorService.shutdown();
```  
◆ invokeAll  
```
ExecutorService executorService = Executors.newCachedThreadPool();
List<Callable<String>> list = new ArrayList<>(4);
list.add(new InnerCallable(2000));
list.add(new InnerCallable(3000));
list.add(new InnerCallable(4000));
LogTrack.w("开始");
List<Future<String>> invokeAll = executorService.invokeAll(list);
invokeAll.forEach(new Consumer<Future<String>>() {
    @Override
    public void accept(Future<String> stringFuture) {
        try {
            LogTrack.w(stringFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
});
executorService.shutdown();
```
