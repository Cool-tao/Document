###### 关于Activity任务栈
> 如果历史栈中包含Activity，打开此Activity从栈中放到栈顶层而不是从新打开Activity
```
Intent intent = new Intent(ReorderFour.this, ReorderTwo.class);
intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
startActivity(intent);
```