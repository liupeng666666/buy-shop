package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyMumberInterface;
import com.whp.buyshop.buy.Interface.SubTaskInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("SubTask")
public class SubTaskController {

    @Autowired
    private SubTaskInterface subTaskInterface;

    //    @PostMapping("BuyMumberSelect")
//    public JSONObject BuyMumberSelect(String name, HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        json=buyMumberInterface.BuyMumberSelect(buyid,name);
//        return json;
//    }
//    @PostMapping("BuyMumberSelectByBuyid")
//    public JSONObject BuyMumberSelectByBuyid(HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        json=buyMumberInterface.BuyMumberSelectByBuyid(buyid);
//        return json;
//    }
//    @PostMapping("BuyMumberSelectByBuy")
//    public JSONObject BuyMumberSelectByBuy(HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        json=buyMumberInterface.BuyMumberSelectByBuy(buyid);
//        return json;
//    }
//    @PostMapping("BuyMumberSelectByTotal")
//    public JSONObject BuyMumberSelectByTotal( HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        json=buyMumberInterface.BuyMumberSelectByTotal(buyid);
//        return json;
//    }
    @PostMapping("SubTaskInsert")
    public JSONObject BuyMumberInsert(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("buyid", buyid);
        map.put("userid", userid);
        json = subTaskInterface.SubTaskInsert(map);
        return json;
    }
//
//    @PostMapping("BuyMumberUpdate")
//    public JSONObject BuyMumberUpdate(@RequestParam Map<String,Object> map, HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
//        map.put("buyid",buyid);
//        map.put("userid",userid);
//        json=buyMumberInterface.BuyMumberUpdate(map);
//        return json;
//    }
//
//    @PostMapping("BuyMumberState")
//    public JSONObject BuyMumberState(@RequestParam("pid[]")String [] pid,@RequestParam Map<String,Object> map, HttpServletRequest request){
//        JSONObject json = new JSONObject();
//        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
//        String userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
//        map.put("buyid",buyid);
//        map.put("userid",userid);
//        for(String id:pid){
//            map.put("pid",id);
//            json=buyMumberInterface.BuyMumberUpdate(map);
//        }
//        return json;
//    }


}
