package org.alex.util.logtrack;

/**
 * 作者：Alex
 * 时间：2017/10/28 22:16
 * 简述：
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class StackTraceElementWrapper {
    private String fullText;
    private StackTraceElement stackTraceElement;

    public StackTraceElementWrapper(StackTraceElement stackTraceElement) {
        this.stackTraceElement = stackTraceElement;
        fullText = stackTraceElement.toString();
//            System.out.println("LogT  " + getFileLineFormat());
//            System.out.println("LogT  " + getFileNameEx());
//            System.out.println("LogT  " + getFileName());
//            System.out.println("LogT  " + getClassName());
//            System.out.println("LogT  " + getSimpleClassName());
//            System.out.println("LogT  " + getMethodName());
    }

    /**
     * 没有 扩展名的 文件名
     * MainActivity
     */
    public String getFileName() {
        String fileLineFormat = getFileLineFormat();
        int endIndex = fileLineFormat.indexOf('.');
        return fileLineFormat.substring(0, endIndex);
    }

    /**
     * 带有 扩展名的 文件名
     * MainActivity.kt
     * MainActivity.java
     */
    public String getFileNameEx() {
        String fileLineFormat = getFileLineFormat();
        int endIndex = fileLineFormat.indexOf(':');
        return fileLineFormat.substring(0, endIndex);
    }

    /**
     * 这种风格的
     * MainActivity.kt:12
     * MainActivity.java:12
     */
    public String getFileLineFormat() {
        int startIndex = fullText.indexOf('(') + 1;
        int endIndex = fullText.indexOf(')');
        if (startIndex <= 0) {
            startIndex = 0;
        }
        if (startIndex >= fullText.length() - 1) {
            startIndex = fullText.length() - 1;
        }
        if (endIndex <= 0) {
            endIndex = 0;
        }
        if (endIndex >= fullText.length() - 1) {
            endIndex = fullText.length() - 1;
        }
        return fullText.substring(startIndex, endIndex);
    }

    /**
     * 带包名的 全类名
     */
    public String getClassName() {
        return stackTraceElement.getClassName();
    }


    /**
     * 不带包名的 类名
     */
    public String getSimpleClassName() {
        String className = stackTraceElement.getClassName();
        int lastIndexOf = className.lastIndexOf('.');
        return className.substring(lastIndexOf + 1, className.length());
    }

    /**
     * 只要 匿名内部类  类名
     */
    public String getInnerSimpleClassName() {
        String className = stackTraceElement.getClassName();
        if (!className.contains("$")) {
            return "";
        }
        int lastIndexOf = className.lastIndexOf('.');
        return className.substring(lastIndexOf + 1, className.length());
    }

    public String getMethodName() {
        return stackTraceElement.getMethodName();
    }

    public String getPackageName() {
        String className = stackTraceElement.getClassName();
        int lastIndexOf = className.lastIndexOf('.');
        return className.substring(0, lastIndexOf);
    }

    public String prefixFormat() {
        return "[ (" + getFileLineFormat() + ") "
                + getInnerSimpleClassName()
                + "#" + getMethodName() + "] ";
    }
}