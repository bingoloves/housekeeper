package com.github.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.github.base.debug.LoggerActivity;
import com.github.base.R;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.PermissionListener;
import com.yhao.floatwindow.Screen;
import com.yhao.floatwindow.ViewStateListener;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志窗口工具
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/13
 */

public class FloatWindowUtils {
    private FloatWindowUtils(){}

    private static class LoggerWindowUtilsHolder{
        private static FloatWindowUtils INSTANCE = new FloatWindowUtils();
    }

    public static FloatWindowUtils getInstance(){
        return LoggerWindowUtilsHolder.INSTANCE;
    }

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        @Override
        public void onPositionUpdate(int x, int y) {

        }

        @Override
        public void onShow() {

        }

        @Override
        public void onHide() {

        }

        @Override
        public void onDismiss() {

        }

        @Override
        public void onMoveAnimStart() {

        }

        @Override
        public void onMoveAnimEnd() {

        }

        @Override
        public void onBackToDesktop() {

        }
    };
    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {

        }

        @Override
        public void onFail() {

        }
    };
    /**
     * 是否创建过了
     */
    private boolean isInit = false;
    public void init(){
        if (!isInit){
            isInit = true;
            Context context = Utils.getApp().getApplicationContext();
            View window = LayoutInflater.from(context).inflate(R.layout.layout_float_window,null);
            FloatWindow
                    .with(context)
                    .setView(window)
                    .setWidth(100)                               //设置控件宽高
                    .setHeight(Screen.width,0.2f)
                    .setX(Screen.width,0.8f)              //设置控件初始位置
                    .setY(Screen.height,0.8f)
                    .setDesktopShow(false)                        //桌面显示
                    .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
                    .setPermissionListener(mPermissionListener)  //监听权限申请结果
                    .build();
            window.setOnClickListener(v -> {
//                showMenuDialog();
//                Intent intent = new Intent(context, LoggerActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
                Activity activity = ActivityStackManager.getStackManager().currentActivity();
                if (activity != null){
                    Navigator.with(activity).navigate(LoggerActivity.class);
                }
            });
        }
    }
    private void showMenuDialog(){
        Activity activity = ActivityStackManager.getStackManager().currentActivity();
        if (activity != null){
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            View view = LayoutInflater.from(activity).inflate(R.layout.layout_float_menu,null);
            dialog.setView(view);
            dialog.setCancelable(true);
            dialog.show();
        }
    }
    /**
     * 显示窗口
     */
    public void show(){
        if (isInit){
            boolean showing = FloatWindow.get().isShowing();
            if (!showing){
                FloatWindow.get().show();
            }
        }
    }

    /**
     * 隐藏窗口
     */
    public void hide(){
        if (isInit){
            FloatWindow.get().hide();
        }
    }
}
