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
public @interface LogStackIndex {
    int Android = 4;
    int AndroidExtension = 6;
    int JAVA = 3;
    int JavaExtension = 5;
}