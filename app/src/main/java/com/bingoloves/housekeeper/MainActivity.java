package com.bingoloves.housekeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.base.bean.Bill;
import com.github.base.core.AbsBaseActivity;
import com.github.base.debug.Logger;
import com.github.base.log.LogUtils;
import com.github.base.utils.DBHelper;

import java.util.List;

public class MainActivity extends AbsBaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        showToolbar(true);
        mCommonToolbar.setCenterTitle("首页");
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            boolean success = Logger.post("Image", "啥技术的建安大道拉达克打算看来都是卡达克拉拉裤多拉点卡立冬快乐打算看到了");
            if (success) toast("插入数据成功");
        });
        findViewById(R.id.btn_bill).setOnClickListener(v -> {
            boolean success = DBHelper.saveBill();
            if (success) toast("插入数据成功");
        });
        findViewById(R.id.btn_bill_query).setOnClickListener(v -> {
            List<Bill> list = DBHelper.getAllBill();
            if (list != null){
                for (Bill bill : list) {
                    LogUtils.e(bill.toString());
                }
            }
        });
    }
}
