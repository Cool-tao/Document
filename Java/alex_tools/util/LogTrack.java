package org.alex.util;

import org.alex.util.logtrack.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Alex
 * 时间：2017/7/19 11:59
 * 简述：
 */
@SuppressWarnings({"unused", "DanglingJavadoc", "ForLoopReplaceableByForEach", "StatementWithEmptyBody", "ConstantConditions", "WeakerAccess", "MismatchedQueryAndUpdateOfCollection"})
public class LogTrack {
    private static final String mainContextNameList[] = new String[5];
    private static PrintTerminal printTerminal;

    private static boolean debug() {
        return BaseUtil.debug();
    }

    private static String headTag() {
        return BaseUtil.headTag();
    }

    private static int minLogLevel() {
        return BaseUtil.minLogLevel();
    }

    private static final int defaultStackIndex = LogStackIndex.JAVA;

    private LogTrack() {
    }

    public static void v(Object msg) {
        boolean isPrintTrack = (msg instanceof Throwable);
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.VERBOSE, defaultStackIndex, stackTrackEnum);
    }

    public static void v(Object msg, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.VERBOSE, defaultStackIndex, stackTrackEnum);
    }

    public static void d(Object msg) {
        boolean isPrintTrack = (msg instanceof Throwable);
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.DEBUG, defaultStackIndex, stackTrackEnum);
    }

    public static void d(Object msg, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.DEBUG, defaultStackIndex, stackTrackEnum);

    }


    public static void i(Object msg) {
        boolean isPrintTrack = (msg instanceof Throwable);
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.INFO, defaultStackIndex, stackTrackEnum);
    }


    public static void i(Object msg, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.INFO, defaultStackIndex, stackTrackEnum);
    }

    public static void w(Object msg) {
        boolean isPrintTrack = (msg instanceof Throwable);
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.WARN, defaultStackIndex, stackTrackEnum);
    }


    public static void w(Object msg, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.WARN, defaultStackIndex, stackTrackEnum);
    }

    public static void e(Object msg) {
        boolean isPrintTrack = (msg instanceof Throwable);
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.ERROR, defaultStackIndex, stackTrackEnum);
    }

    public static void e(Object msg, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(msg, LogLevel.WARN, defaultStackIndex, stackTrackEnum);
    }

    protected static void setMainContext(String... pgName) {
        for (int i = 0; pgName != null && i < pgName.length && i <= mainContextNameList.length; i++) {
            mainContextNameList[i] = pgName[i];
        }
    }

    public static void printLog(Object text, @LogLevel int logLevel, @LogStackIndex int stackIndex) {
        printThreadStackTrack(text, logLevel, stackIndex, StackTrackEnum.ONE_STACK);
    }

    public static void printLog(Object text, @LogLevel int logLevel, @LogStackIndex int stackIndex, boolean isPrintTrack) {
        int stackTrackEnum = isPrintTrack ? StackTrackEnum.ALL_STACK : StackTrackEnum.ONE_STACK;
        printThreadStackTrack(text, logLevel, stackIndex, stackTrackEnum);
    }

    public static void printMainContext(Object text, @LogLevel int logLevel) {
        printThreadStackTrack(text, logLevel, defaultStackIndex, StackTrackEnum.MAIN_CONTEXT_STACK);
    }

    public static void printStackTrace(Throwable throwable) {
        printThreadStackTrack(throwable, LogLevel.ERROR, defaultStackIndex, StackTrackEnum.ALL_STACK);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "StringConcatenationInsideStringBufferAppend"})
    private static void printThreadStackTrack(Object text, @LogLevel int logLevel, @LogStackIndex int stackIndex, @StackTrackEnum int stackTrackEnum) {
        if (!debug()) {
            return;
        }
        String tag = headTag();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTraceElements.length; i++) {
            //System.out.println(i + "   LogT  ==   " + stackTraceElements[i]);
        }
        stackIndex = Math.min((stackTraceElements.length - 1), stackIndex);

        boolean isPrintTrack = stackTrackEnum == StackTrackEnum.ALL_STACK || stackTrackEnum == StackTrackEnum.MAIN_CONTEXT_STACK;
        List<String> stackContentList = new ArrayList<>();
        for (int i = stackTraceElements.length - 1; (i >= 0) && isPrintTrack; i--) {
            StackTraceElementWrapper stackTraceElementWrapper = new StackTraceElementWrapper(stackTraceElements[i]);
            String prefix = stackTraceElementWrapper.prefixFormat();
            boolean isThreadStackTrackInMainContext = false;
            if (stackTrackEnum == StackTrackEnum.MAIN_CONTEXT_STACK) {
                String packageName = stackTraceElementWrapper.getPackageName();
                for (int j = 0; j < mainContextNameList.length; j++) {
                    isThreadStackTrackInMainContext = contains(packageName, mainContextNameList[j]);
                    if (isThreadStackTrackInMainContext) {
                        //System.out.println("org.alex.util.logtrack.LogTrack  " + packageName + "    " + mainContextNameList[j] + "   在主包名下 " + isThreadStackTrackInMainContext+"  "+prefix);
                        //System.out.println("org.alex.util.logtrack.LogTrack  " + i + "   " + j + "  " + packageName);
                        break;
                    }
                }
            }
            if (isMineOwnThreadAndCanPrintTrack(prefix) && stackTrackEnum == StackTrackEnum.MAIN_CONTEXT_STACK && isThreadStackTrackInMainContext) {
                stackContentList.add(prefix);
            }
            if (isMineOwnThreadAndCanPrintTrack(prefix) && stackTrackEnum == StackTrackEnum.ALL_STACK) {
                stackContentList.add(prefix);
            }
        }

        for (int i = 0; stackContentList != null && i < stackContentList.size() - 1; i++) {
            printWithTerminal(logLevel, tag, "调用顺序    --> " + stackContentList.get(i));
        }

        StackTraceElementWrapper stackTraceElementWrapper = new StackTraceElementWrapper(stackTraceElements[stackIndex]);
        String message = text == null ? "  " : text.toString();
        String prefix = (isPrintTrack ? "调用顺序    --> " : "") + stackTraceElementWrapper.prefixFormat();
        int splitLength = 3000;
        int splitCount = message.length() / splitLength;
        for (int index = 0; index <= splitCount; index++) {
            int startIndex = index * splitLength;
            startIndex = Math.min(startIndex, message.length());
            int endIndex = startIndex + splitLength;
            endIndex = Math.min(endIndex, message.length());
            if (startIndex != endIndex) {
                printWithTerminal(logLevel, tag, prefix + message.substring(startIndex, endIndex));
            }
        }

    }

    protected static void logTerminal(PrintTerminal printTerminal) {
        LogTrack.printTerminal = printTerminal;
    }

    private static void printWithTerminal(int logLevel, String tag, String message) {
        printTerminal = (printTerminal == null) ? new JvmConsole() : printTerminal;
        if (logLevel < minLogLevel()) {
            return;
        }
        printTerminal.printWithTerminal(logLevel, tag, message);
    }

    private static boolean isMineOwnThreadAndCanPrintTrack(String contents) {
        String array[] = new String[]{//FutureTask
                " [ (Method", " [ (Looper", " [ (Handler",
                "[ (ActivityThread", "[ (Instrumentation", "[ (Activity",
                "[ (Thread", "[ (VMStack", "[ (ZygoteInit",
                "[ (Native Method", "[ (ScheduledThreadPoolExecutor", "[ (FutureTask",
                "[ (ScheduledRunnable", "[ (Scheduler", "[ (ObservableSubscribeOn",
                "[ (Observable", "[ (ObservableOnErrorNext", "[ (ObservableMap",
                "[ (BasicFuseableObserver",
                "[ (" + LogTrack.class.getSimpleName()};
        boolean isMine = true;
        for (String anArray : array) {
            if (contents.startsWith(anArray)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    private static boolean contains(Object words, Object letter) {
        if (words == null && letter == null) {
            return true;
        }
        if (words == null && letter != null) {
            return false;
        }
        if (words != null && letter == null) {
            return false;
        }
        return words.toString().contains(letter.toString());
    }

}
