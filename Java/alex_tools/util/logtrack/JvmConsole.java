package org.alex.util.logtrack;

/**
 * 作者：Alex
 * 时间：2017/10/20 23:02
 * 简述：
 */
public class JvmConsole implements PrintTerminal {

    @Override
    public void printWithTerminal(int logLevel, String tag, String message) {
        switch (logLevel) {
            case LogLevel.DEBUG:
                System.out.println(tag + "  DEBUG  " + message);
                break;
            case LogLevel.INFO:
                System.out.println(tag + "  INFO  " + message);
                break;
            case LogLevel.WARN:
                System.out.println(tag + "  WARN  " + message);
                break;
            case LogLevel.ERROR:
                System.err.println(tag + "  ERROR  " + message);
                break;
        }
    }
    
}
