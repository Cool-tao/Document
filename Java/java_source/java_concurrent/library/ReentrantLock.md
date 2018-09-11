### ReentrantLock  
ReentrantLock类似于synchronized的增强版，synchronized的特点是使用简单，一切交给JVM去处理，但是功能上是比较薄弱的。  
相比于synchronized，ReentrantLock在功能上更加丰富，它具有可重入、可中断、可限时、公平锁等特点。  
两者性能是不相上下的。如果是简单的实现，不要刻意去使用ReentrantLock。  

### lock  简单的 锁  
```
private static final class MyRunnable implements Runnable {
        private final ReentrantLock lock = new ReentrantLock();
        int count = 0;

        @Override
        public void run() {
            lock.lock();
            ThreadUtil.sleep(1000);
            count++;
            LogTrack.w(Thread.currentThread().getName() + ", count = " + count);
            lock.unlock();
        }
    }
```
### tryLock  超时 锁
多个线程执行，如果有一个超时了，A代码块 就不会再有锁了，  

```
public void run() {
    ThreadUtil.tryLock(lock, 500);
    {  // A 代码块
    ThreadUtil.sleep(1000);
    count++;
    LogTrack.w(Thread.currentThread().getName() + ", count = " + count);
    }
    if (lock.isHeldByCurrentThread()) {
        lock.unlock();
    }
}
```
