### 标准写法 

```
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle bundle = new Bundle();
    MessageStartEntity entity = getIntent().getParcelableExtra("entity");
    handlerThread = new HandlerThread("HandlerThread");
    handlerThread.start();
    Handler handler = new Handler(handlerThread.getLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 处理 事件
        }
    };
    new Thread(new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }).start();
}

@Override
protected void onDestroy() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        handlerThread.quitSafely();
    } else {
        handlerThread.quit();
    }
    super.onDestroy();
}
```