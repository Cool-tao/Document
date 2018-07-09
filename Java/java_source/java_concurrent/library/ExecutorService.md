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

##### API  
```
// 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。
boolean awaitTermination(long timeout, TimeUnit unit)
// 执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
// 执行给定的任务，当所有任务完成或超时期满时（无论哪个首先发生），返回保持任务状态和结果的 Future 列表。
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
// 执行给定的任务，如果某个任务已成功完成（也就是未抛出异常），则返回其结果。
<T> T invokeAny(Collection<? extends Callable<T>> tasks)
// 执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。
<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
// 如果此执行程序已关闭，则返回 true。
boolean isShutdown()
// 如果关闭后所有任务都已完成，则返回 true。
boolean isTerminated()
// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
void shutdown()
// 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
List<Runnable> shutdownNow()
// 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。
<T> Future<T> submit(Callable<T> task)
// 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
Future<?> submit(Runnable task)
// 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
<T> Future<T> submit(Runnable task, T result)
```
