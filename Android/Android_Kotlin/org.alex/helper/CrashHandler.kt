package org.alex.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import org.alex.util.logE
import java.io.*
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*


/**
 * 作者：Alex
 * 时间：2017/5/31 9:38
 * 简述：
 */


@SuppressLint("SimpleDateFormat")
class CrashHandler : Thread.UncaughtExceptionHandler {

    /**
     * 存储 错误日志的 路径
     */
    private val logPath: String
    private val context: Context
    private var onCrashListener: OnCrashListener? = null

    /**
     * 不需要包含 SD 根目录
     */
    constructor(context: Context) : this(context, "本地日志" + File.separator + "cash.log") {}

    /**
     * 不需要包含 SD 根目录
     */
    constructor(context: Context, onCrashListener: OnCrashListener) : this(context, "本地日志" + File.separator + "cash.log", onCrashListener) {}

    /**
     * 不需要包含 SD 根目录
     */
    @JvmOverloads constructor(context: Context, logPath: String, onCrashListener: OnCrashListener? = null) {
        this.logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + logPath
        this.context = context
        this.onCrashListener = onCrashListener
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    fun setOnCrashListener(onCrashListener: OnCrashListener) {
        this.onCrashListener = onCrashListener
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        val errorBuilder = StringBuilder()
        errorBuilder.append("\n" + getStackTraceString(ex))

        val currentTime = System.currentTimeMillis()
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(currentTime))
        val phoneInfo = phoneInfo
        val cashMessage = time + "\n" + phoneInfo + errorBuilder.toString()
        if (onCrashListener != null) {
            onCrashListener!!.onCrash(cashMessage)
        }
        cashMessage.logE()
        saveFile(cashMessage, false)
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    private val phoneInfo: String
        get() {
            val packageManager = context.getPackageManager()
            try {
                val packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES)
                val stringBuilder = StringBuilder()
                stringBuilder.append(" versionName : " + packageInfo.versionName)
                stringBuilder.append("\n versionCode : " + packageInfo.versionCode)
                stringBuilder.append("\n OS  version : " + Build.VERSION.RELEASE)
                stringBuilder.append("\n 制造商 : " + Build.MANUFACTURER)
                stringBuilder.append("\n手机型号 : " + Build.MODEL)
                stringBuilder.append("\n cpu架构 : " + Build.CPU_ABI + "  " + Build.CPU_ABI2)
                return stringBuilder.toString()
            } catch (e: Exception) {
                e.logE()
            }

            return ""
        }

    /**
     * 保存文件

     * @param append true-追加|false-清空
     */
    private fun saveFile(result: String, append: Boolean): Boolean {
        var output: FileOutputStream? = null
        try {
            val file = File(logPath)
            val parentStr = file.getParent()
            val createSDCardDir = createSDCardDir(parentStr)
            if (!createSDCardDir) {
                return false
            }
            output = FileOutputStream(File(logPath), append)
            output!!.write(result.toByteArray())
            output!!.flush()
            return true
        } catch (e: Exception) {
            e.logE()
        } finally {
            try {
                if (output != null) {
                    output!!.close()
                }
            } catch (e: IOException) {
                e.logE()
            }

        }
        return false
    }

    private fun createSDCardDir(newPath: String): Boolean {
        if (Environment.getExternalStorageDirectory().getAbsolutePath() == null) {
            return false
        }
        //得到一个路径，内容是sdcard的文件夹路径和名字
        //LogUtils.e("newPath = "+newPath);
        val path1 = File(newPath)
        if (!path1.exists()) {
            //若不存在，创建目录，可以在应用启动的时候创建
            val mkdirs = path1.mkdir()
            //LogUtils.e("创建"+path1.getAbsolutePath()+" 成功 = "+mkdirs);
            return mkdirs
        }
        return true
    }

    private fun getStackTraceString(tr: Throwable?): String {
        if (tr == null) {
            return ""
        }
        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        var t = tr
        while (t != null) {
            if (t is UnknownHostException) {
                return ""
            }
            t = t.cause
        }
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

    interface OnCrashListener {
        fun onCrash(error: String)
    }
}
/**
 * 不需要包含 SD 根目录
 */


