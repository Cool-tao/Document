package org.alex.helper.library;

import android.content.Intent;
import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：Alex
 * 时间：2016/11/3 13:30
 * 简述：
 */
@SuppressWarnings("all")
@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@IntDef(value = {IntentFlag.STANDARD, IntentFlag.SING_TOP, IntentFlag.SING_TASK, IntentFlag.CLEAR_TASK, IntentFlag.REORDER_2_FRONT, IntentFlag.NO_HISTORY, IntentFlag.FORWARD_RESULT, IntentFlag.PREVIOUS_IS_TOP})
public @interface IntentFlag {
    /**
     * 等于清单文件的 STANDARD
     */
    public static final int STANDARD = Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * 等于清单文件的 SING_TOP， start之后，之前Activity的数据会保留不变
     */
    public static final int SING_TOP = Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK;
    /**
     * 等于清单文件的 SING_TASK， start之后，之前Activity的数据会保留不变
     */
    public static final int SING_TASK = Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * 比如说原来栈中情况是A,B,AppCon,D,在D中启动B(加入该flag),中间过程是A,B,C依次destory,
     * D先onPause,随后BonCreate,onStart,onResume.D再onStop,onDestory.最后只有一个B在栈底.(无论taskAffinity..?)
     */
    public static final int CLEAR_TASK = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * 比如说原来栈中情况是A,B,AppCon,D,在D中启动B(加入该flag)，栈中的情况会是A,AppCon,D,B.(调用onNewIntent())
     */
    public static final int REORDER_2_FRONT = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * A启动B(加入该Flag),B启动C.在C返回,将直接返回到A.B在A正常onResume后,才会调用onStop,onDestory...
     * 而且被这个flag启动的activity,它的onActivityResult()永远不会被调用
     */
    public static final int NO_HISTORY = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * 多个Activity的值传递。A通过startActivityForResult启动B,B启动C，但B为过渡页可以finish了，
     * A在期望C把结果返回. 这种情况,B可以在启动C的时候加入该flag.
     */
    public static final int FORWARD_RESULT = Intent.FLAG_ACTIVITY_FORWARD_RESULT | Intent.FLAG_ACTIVITY_NEW_TASK;

    /**
     * 即 A---> B --->AppCon，若B启动C时用了这个标志位，那在启动时B并不会被当作栈顶的Activity，而是用A做栈顶来启动C。
     * 此过程中B充当一个跳转页面。
     * 典型的场景是在应用选择页面，如果在文本中点击一个网址要跳转到浏览器，而系统中又装了不止一个浏览器应用，
     * 此时会弹出应用选择页面。在应用选择页面选择某一款浏览器启动时，就会用到这个Flag。
     * 然后应用选择页面将自己finish，以保证从浏览器返回时不会在回到选择页面。经常与FLAG_ACTIVITY_FORWARD_RESULT 一起使用。
     */
    public static final int PREVIOUS_IS_TOP = Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_FORWARD_RESULT | Intent.FLAG_ACTIVITY_NEW_TASK;
}
