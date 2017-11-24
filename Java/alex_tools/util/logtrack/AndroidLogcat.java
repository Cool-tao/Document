package org.alex.util.logtrack;

/**
 * 作者：Alex
 * 时间：2017/10/20 23:02
 * 简述：
 */
public class AndroidLogcat implements PrintTerminal {

    @Override
    public void printWithTerminal(int logLevel, String tag, String message) {
        switch (logLevel) {
            case LogLevel.VERBOSE:
//                android.util.Log.v(tag, message);
                break;
            case LogLevel.DEBUG:
//                android.util.Log.d(tag, message);
                break;
            case LogLevel.INFO:
//                android.util.Log.i(tag, message);
                break;
            case LogLevel.WARN:
//                android.util.Log.w(tag, message);
                break;
            case LogLevel.ERROR:
//                android.util.Log.e(tag, message);
                break;
        }
    }
}
