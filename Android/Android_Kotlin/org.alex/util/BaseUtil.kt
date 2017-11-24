package org.alex.util

import android.app.Application
import android.content.Context
import org.alex.helper.CrashHandler


/**
 * 作者：Alex
 * 时间：2017/5/31 9:31
 * 简述：
 */
object BaseUtil {
    private var crashHandler: CrashHandler? = null
    private var isDebug = true
    var headTag = "LogTrack"
    lateinit var application: Application
    val context: Context
        get() = application

    /**
     * 初始化 Application
     */
    fun init(app: Application): BaseUtil {
        application = app
        crashHandler = CrashHandler(app)
        return this
    }


    fun debug(): Boolean {
        return isDebug
    }

    /**
     * 设置 Log 头
     */
    fun debug(debug: Boolean): BaseUtil {
        isDebug = debug
        return this
    }

    /**
     * 设置 Log 头
     */
    fun headTag(headTag: String): BaseUtil {
        this.headTag = headTag
        return this
    }

    /*设置共享存储的文件名称*/
    /*    public BaseUtil spFileName(String fileName) {
        //SpUtil.fileName(fileName);
        return this;
    }*/

    /**
     * 崩溃监听
     */
    fun onCrashListener(onCrashListener: CrashHandler.OnCrashListener): BaseUtil {
        crashHandler!!.setOnCrashListener(onCrashListener)
        return this
    }

    val isContextNull: Boolean
        get() {
            if (application == null) {
                "请在 Application 中初始化 " + BaseUtil::class.java.simpleName + " headFootView(Application application);".logE()
                return true
            }
            return false
        }

    val isContextNotNull: Boolean
        get() = !isContextNull

}


