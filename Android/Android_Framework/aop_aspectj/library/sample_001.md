### 监控 某个Activity启动  
写完 之后 直接 运行 即刻；；  
```
@Aspect   //必须使用@AspectJ标注，这样class DemoAspect就等同于 aspect DemoAspect了
public class PageVisitLogAop {
    /**
     * Pointcut：pointcut也变成了一个注解，这个注解是针对一个函数的，比如此处的logForActivity() 其实它代表了这个pointcut的名字。
     * 如果是带参数的pointcut，则把参数类型和名字放到
     * 代表pointcut名字的logForActivity中，然后在@Pointcut注解中使用参数名。
     * 基本和以前一样，只是写起来比较奇特一点。后面我们会介绍带参数的例子
     */
    @Pointcut("execution(* com.alex.andfun.apt.AptMainActivity.onCreate(..)) ||"
            + "execution(* com.alex.andfun.apt.AptMainActivity.onStart(..))")
    public void activityOnCreatePointcut() {
    }

    /**
     * Before：这就是Before的advice，对于after，after -returning，和after-throwing。对于的注解格式为
     * After，@AfterReturning，@AfterThrowing。Before后面跟的是pointcut名字，然后其代码块由一个函数来实现。比如此处的log。
     */
    @Before("activityOnCreatePointcut()")
    public void activityOnCreateLog(JoinPoint joinPoint) {
        LogTrack.w("当前启动页面:  " + joinPoint.getTarget().getClass().getSimpleName() + "  全路径 = " + joinPoint.getTarget().getClass().getName());
    }

}
```