package org.alex.util;


import org.alex.util.logtrack.LogLevel;
import org.alex.util.logtrack.PrintTerminal;

/**
 * 作者：Alex
 * 时间：2017/8/15 22:23
 * 简述：
 */

public class BaseUtil {
    private static boolean debug = true;
    private static String headTag = LogTrack.class.getSimpleName();
    @LogLevel
    private static int minLog = LogLevel.DEBUG;

    public static void debug(boolean debug) {
        BaseUtil.debug = debug;
    }

    public static boolean debug() {
        return debug;
    }

    /**
     * 最低 打印 级别
     */
    @LogLevel
    public static int minLogLevel() {
        return minLog;
    }

    /**
     * 最低 打印 级别
     * {@link org.alex.util.logtrack.LogLevel#DEBUG}
     */
    public static void minLogLevel(@LogLevel int minLog) {
        BaseUtil.minLog = minLog;
    }

    public static void headTag(String headTag) {
        BaseUtil.headTag = headTag;
    }

    public static String headTag() {
        return headTag;
    }

    public static void mainContext(String... pgName) {
        LogTrack.setMainContext(pgName);
    }

    public static void logTerminal(PrintTerminal printTerminal) {
        LogTrack.logTerminal(printTerminal);
    }

   }

