### 在thread 中使用handler 
一个Handler的标准写法其实是这样的：  
```
texttitle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();  // 必须 prepare  
                    Handler mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.what == 101) {
                                Log.i(TAG, "在子线程中定义Handler，并接收到消息。。。");
                            }
                        }
                    };
                    Looper.loop();
                }
            }.start();
        }
    });
```
