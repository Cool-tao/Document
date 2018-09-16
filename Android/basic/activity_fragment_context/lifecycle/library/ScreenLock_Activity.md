###### 屏幕锁定下 Activity 的生命周期

> 屏幕锁定

```
MainActivity            onCreate
MainActivity            onStart
MainActivity            onResume
/*这个时候按下 电源键 ->锁屏 */
MainActivity            onPause
MainActivity            onStop
```
> 屏幕解锁
```
MainActivity            onRestart
MainActivity            onStart
MainActivity            onResume
```