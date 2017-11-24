package org.alex.util.logtrack;

/**
 * 作者：Alex
 * 时间：2017/10/20 23:09
 * 简述：
 */
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
public @interface StackTrackEnum {

    /**
     * 只打印 一个 stack track
     */
    int ONE_STACK = 0;
    /**
     * 打印 所有 stack track
     */
    int ALL_STACK = 1;
    /**
     * 只打印 main context 集合里面的 stack track
     */
    int MAIN_CONTEXT_STACK = 2;
}