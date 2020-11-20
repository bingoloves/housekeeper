package com.github.base.core;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.github.base.R;
import com.github.base.crash.CaocConfig;
import com.github.base.log.LogUtils;
import com.github.base.utils.DeviceUtil;
import com.github.base.utils.FloatWindowUtils;
import com.github.base.utils.Utils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.litepal.LitePal;

/**
 * Created by bingo on 2020/8/25 0025.
 */

public class BaseApplication extends Application{

    public boolean isDebug(){
        return true;
    }
    private static boolean isDebugModel;

    public static boolean getBuildModel(){
        return isDebugModel;
    }

    /**
     * SmartRefresh 刷新库全局配置头部
     * static 代码段可以防止内存泄露
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context).setArrowResource(R.drawable.ic_arrow_down_default).setEnableLastTime(false));
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) ->  new ClassicsFooter(context).setDrawableSize(20));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        isDebugModel = isDebug();
        initLog();
        initCrash();
        if (isDebugModel){
            FloatWindowUtils.getInstance().init();
        }
        initLitepal();
    }

    private void initLitepal() {
        LitePal.initialize(this);
    }

    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(isDebugModel) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
//                .restartActivity(MainActivity.class) //重新启动后的activity  不配置默认重启App
                .apply();
    }

    /**
     * 初始化自定义Log工具
     */
    private void initLog(){
        LogUtils.init(isDebugModel, DeviceUtil.getAppName(this));
    }
}
