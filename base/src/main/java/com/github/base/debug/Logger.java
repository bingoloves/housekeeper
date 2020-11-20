package com.github.base.debug;

import android.support.annotation.NonNull;

import com.github.base.log.LogUtils;

import org.litepal.LitePal;
import org.litepal.crud.callback.SaveCallback;
import java.util.Date;
import java.util.List;

/**
 * Created by bingo on 2020/11/18.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志查看工具
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/18
 */

public class Logger {
    /**
     * 发送消息 同步
     * @param tag
     * @param msg
     */
    public static synchronized boolean post(@NonNull String tag, String msg){
        Exception exception = new Exception();
        LoggerInfo info = new LoggerInfo();
        info.setTag(tag);
        info.setMessage(msg);
        info.setThreadName(Thread.currentThread().getName());
        info.setLoggerClassName(getClassName(exception));
        info.setMethodName(getMethodName(exception));
        info.setRows(getRows(exception));
        info.setDate(new Date());
        return info.save();
    }

    /**
     * 发送消息 异步
     * @param tag
     * @param msg
     * @param callback
     * @return
     */
    public static void postAsync(@NonNull String tag, String msg,SaveCallback callback){
        Exception e = new Exception();
        LoggerInfo info = new LoggerInfo();
        info.setTag(tag);
        info.setMessage(msg);
        info.setThreadName(Thread.currentThread().getName());
        info.setLoggerClassName(getClassName(e));
        info.setMethodName(getMethodName(e));
        info.setRows(getRows(e));
        info.setDate(new Date());
        info.saveAsync().listen(callback);
    }

    /**
     * 查询全部
     * @return
     */
    public static List<LoggerInfo> getAll(){
        List<LoggerInfo> allMovies = LitePal.findAll(LoggerInfo.class);
        return allMovies;
    }

    /**
     * 删除全部数据
     */
    public static void deleteAll(){
        LitePal.deleteAll(LoggerInfo.class);
    }
    /**
     * 查询带条件
     * @param search
     * @return
     */
    public static List<LoggerInfo> getAllBySearch(String search){
        List<LoggerInfo> queryList = LitePal
//                .where("tag > ?",search)
//                .where("message like ?", "%" + search + "%")
//                .where("methodName like ?", "%" + search + "%")
                .where("tag like ? and message like ? and methodName like ?", "%"+search+"%","%"+search+"%","%"+search+"%")
                .order("date desc").find(LoggerInfo.class);
        return queryList;
    }

    public static int getRows(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        if (trace == null || trace.length == 0) {
            return -1;
        }
        return trace[1].getLineNumber();
    }

    public static String getMethodName(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        if (trace == null || trace.length == 0) {
            return "";
        }
        return trace[1].getMethodName();
    }
    public static String getClassName(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        if (trace == null || trace.length == 0) {
            return "";
        }
        return trace[1].getClassName();
    }
}
