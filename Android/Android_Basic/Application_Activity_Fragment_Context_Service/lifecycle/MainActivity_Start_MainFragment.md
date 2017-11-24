###### Activity启动Fragment
```
MainActivity            onCreate
MainFragment         onAttach
MainFragment         onCreate
MainFragment         onCreateView
MainFragment         onActivityCreated
MainFragment         onStart
MainActivity             onStart
MainActivity             onResume
MainFragment         onResume
```

> 屏幕锁定
```
MainFragment         onPause
MainActivity             onPause
MainFragment         onStop
MainActivity             onStop
```
> 屏幕解锁
```
MainActivity            onRestart
MainFragment        onStart
MainActivity            onStart
MainActivity            onResume
MainFragment        onResume
```

> 正常finish一个Activity
```
MainFragment         onPause
MainActivity             onPause
MainFragment         onStop
MainActivity             onStop
MainFragment         onDestroyView
MainFragment         onDestroy
MainFragment         onDetach
MainActivity             onDestroy
```

> MainActivity 打开 NewActivity
```
MainFragment         onPause
MainActivity             onPause
NewActivity             onCreate
NewActivity             onStart
NewActivity             onResume
MainFragment         onStop
NewActivity              onStop
```

> NewActivity 结束 回到 MainActivity
```
NewActivity              onPause
MainActivity             onRestart
MainFragment         onStart
MainActivity             onStart
MainActivity             onResume
MainFragment        onResume
NewActivity             onStop
NewActivity             onDestroy
```

> 当按下Home
```
MainFragment            onPause
MainActivity                onPause
MainFragment            onStop
MainActivity                onStop
```