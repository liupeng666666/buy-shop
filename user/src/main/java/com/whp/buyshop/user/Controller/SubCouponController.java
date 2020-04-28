package com.whp.buyshop.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Dao.BuyCouponbDao;
import com.whp.buyshop.user.Dao.SubCouponDao;
import com.whp.buyshop.user.Dao.SubUserDao;
import com.whp.buyshop.user.Interface.SubCouponInterface;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("SubCoupon")
public class SubCouponController {
    @Autowired
    private SubCouponInterface subCouponInterface;
    @Autowired
    private BuyCouponbDao buyCouponbDao;

    @PostMapping("SubCouponInsert")
    public JSONObject SubCouponInsert(@RequestParam("userid[]") String[] userid, @RequestParam Map<String, Object> map) {
        JSONObject json = new JSONObject();
        Map<String, Object> map1 = buyCouponbDao.BuyCouponSelectById(map);

        SnowFlake snowFlake = new SnowFlake(1, 1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        map.put("opentime", df.format(date));
        if (map1.containsKey("endTime")) {
            if (map1.get("endTime").toString() != null || !("").equals(map1.get("endTime").toString())) {
                map.put("endtime", map1.get("endTime").toString());
            }
        }
        if (!map1.get("cycle").toString().equals("0")) {
            int cycle = Integer.parseInt(map1.get("cycle").toString());
            map.put("endtime", df.format(new Date(date.getTime() + cycle * 24 * 60 * 60 * 1000)));
        }
        for (String id : userid) {
            map.put("pid", snowFlake.nextId());
            map.put("userid", id);
            json = subCouponInterface.SubCouponInsert(map);
        }
        if (json.getInteger("code") == 100) {
            map.put("w_number", userid.length);
            buyCouponbDao.BuyCouponUpdateNumber(map);
        }
        return json;
    }

}
