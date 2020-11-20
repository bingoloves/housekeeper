package com.github.base.debug;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.github.base.R;
import com.github.base.adapter.recyclerview.CommonAdapter;
import com.github.base.adapter.recyclerview.base.ViewHolder;
import com.github.base.adapter.recyclerview.wrapper.EmptyWrapper;
import com.github.base.core.AbsBaseActivity;
import com.github.base.utils.DeviceUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import org.simple.eventbus.EventBus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bingo on 2020/11/13.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 日志查看工具页面
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/13
 */

public class LoggerActivity extends AbsBaseActivity implements OnRefreshLoadMoreListener {
    private EditText searchEt;
    private TextView searchTv;
    private SmartRefreshLayout smartRefresh;
    private RecyclerView mRecyclerView;
    private CommonAdapter<LoggerInfo> mAdapter;
    private EmptyWrapper emptyWrapper;
    //全量数据
    private List<LoggerInfo> loggerList = new ArrayList<>();
    //当前数据
    //private List<LoggerInfo> filterList = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    //分页参数
    private int page = 1;
    private int pageSize = 10;
    //是否有更多数据
    private boolean hasMore = true;
    @Override
    public int getContentView() {
        return R.layout.activity_logger;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        EventBus.getDefault().registerSticky(this);
        showToolbar(true);
        initView();
        getRealmData();
    }
    private void getRealmData() {
        loggerList = Logger.getAllBySearch(searchEt.getText().toString().trim());
        List<LoggerInfo> currentList = new ArrayList<>();
        if (loggerList != null){
            if (loggerList.size() <= pageSize){
                currentList = loggerList;
                hasMore = false;
            } else {
                currentList = loggerList.subList(0,pageSize);
                hasMore = true;
            }
        }
        mAdapter.update(currentList);
        emptyWrapper.notifyDataSetChanged();
        smartRefresh.finishRefresh();
        setRightMenuNum();
    }

    private void initView() {
        searchEt = findViewById(R.id.et_search);
        searchTv = findViewById(R.id.btn_search);
        smartRefresh = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recyclerView);

        mCommonToolbar.setCenterTitle(DeviceUtil.getAppName()+"(日志)");
        mCommonToolbar.setBackground(getResources().getColor(android.R.color.white));
        mCommonToolbar.back(v -> finish());
        mCommonToolbar.setRightTitle("清空", v -> {
            loggerList.clear();
            mAdapter.clear();
            emptyWrapper.notifyDataSetChanged();
            Logger.deleteAll();
            toast("已清空");
        });
        mCommonToolbar.showBaseLine();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommonAdapter<LoggerInfo>(this,R.layout.layout_logger_item,loggerList) {
            @Override
            protected void convert(ViewHolder holder, LoggerInfo bean, int position) {
                holder.setText(R.id.tv_tag,(position+1)+"、["+bean.getTag()+"]");
                holder.setText(R.id.tv_time,getDateTime(bean.getDate()));
                holder.setText(R.id.tv_thread,"当前线程："+bean.getThreadName());
                holder.setText(R.id.tv_class,bean.getLoggerClassName()+">"+ bean.getMethodName()+"["+bean.getRows()+"]");
                holder.setText(R.id.tv_message,bean.getMessage());
            }
        };

        emptyWrapper = new EmptyWrapper(mAdapter);
        emptyWrapper.setEmptyView(R.layout.layout_empty_view);
        mRecyclerView.setAdapter(emptyWrapper);
        smartRefresh.setOnRefreshLoadMoreListener(this);
        searchEt.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                getRealmData();
                return true;
            }
            return false;
        });
        searchTv.setOnClickListener(v -> getRealmData());
    }

    /**
     * 更新右侧菜单 当前一共的日志数
     */
    private void setRightMenuNum(){
        List<LoggerInfo> datas = mAdapter.getDatas();
        if (datas != null && datas.size()>0){
            mCommonToolbar.setRightTitle("清理（共"+loggerList.size()+"条）");
        } else {
            mCommonToolbar.setRightTitle("清理");
        }
    }

    /**
     * 获取格式化时间
     * @return
     */
    private String getDateTime(Date date){
        return simpleDateFormat.format(date);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (hasMore){
            if (loggerList != null){
                List<LoggerInfo> infoList = new ArrayList<>();
                page++;
                if (mAdapter.getDatas().size() + pageSize<loggerList.size()){
                    infoList = loggerList.subList((page-1)*pageSize,page*pageSize);
                    hasMore = true;
                } else {
                    infoList = loggerList.subList((page-1)*pageSize,loggerList.size());
                    hasMore = false;
                }
                mAdapter.addAll(infoList);
                emptyWrapper.notifyDataSetChanged();
                smartRefresh.finishLoadMore();
            }
        } else {
            toast("没有更多数据");
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        getRealmData();
    }
}
