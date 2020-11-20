package com.github.base.debug;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志打印信息详情
 * @UpdateUser: bingo
 * @UpdateDate: 2020/11/13
 */
public class LoggerInfo extends LitePalSupport {
    //继承 LitePalSupport，不能包含List类型的属性（非LitePal支持的类型），否则会报异常
    //litepal支持的实体类字段映射类型为 int，long，double，float，byte[]，boolean，String，Date；不支持String[]数组型
    /**
     * 日志标识
     */
    private String tag;
    /**
     * 日志内容
     */
    private String message;
    /**
     * 线程名
     */
    private String threadName;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 行数
     */
    private int rows;
    /**
     * 时间
     */
    private Date date;


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLoggerClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setLoggerClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
