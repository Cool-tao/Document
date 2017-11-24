package org.alex.helper.library;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：Alex
 * 时间：2017/1/8 21 26
 * 简述：
 */
@SuppressWarnings("all")
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@IntDef(value = {Shape.LINE, Shape.OVAL, Shape.RECTANGLE, Shape.RING})
public @interface Shape {
    int RECTANGLE = GradientDrawable.RECTANGLE;
    int OVAL = GradientDrawable.OVAL;
    int LINE = GradientDrawable.LINE;
    int RING = GradientDrawable.RING;
}
