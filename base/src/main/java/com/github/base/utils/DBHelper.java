package com.github.base.utils;

import com.github.base.bean.Bill;
import com.github.base.debug.LoggerInfo;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

/**
 * Created by bingo on 2020/11/20.
 *
 * @Author: bingo
 * @Email: 657952166@qq.com
 * @Description: 数据库操作类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/20
 */

public class DBHelper {
    public static boolean saveBill(){
        Bill bill = new Bill();
        bill.setAddress("振兴路地铁口");
        bill.setCategory(1);
        bill.setConsumePlaceName("振兴路地铁口");
        bill.setDate(new Date());
        bill.setGoodsName("恰恰瓜子");
        bill.setGoodsNum(2);
        bill.setGoodsPrice(8);
        bill.setDesc("超市购买");
        bill.setGoodsPrice(100);
        return bill.save();
    }

    /**
     * 获取全部数据
     * @return
     */
    public static List<Bill> getAllBill(){
        List<Bill> bills = LitePal.findAll(Bill.class);
        return bills;
    }
}
