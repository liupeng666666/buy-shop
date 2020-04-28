package com.whp.buyshop.buy.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.BuyGoodsInterface;
import com.whp.buyshop.utils.util.ImgUtil;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2019/10/10 10:30
 * @descrpition :
 */
@RestController
@RequestMapping("BuyGoods")
public class BuyGoodsController {

    @Autowired
    private BuyGoodsInterface buyGoodsInterface;
    @Autowired
    private ImgUtil imgUtil;
    @Value("${fast.url}")
    private String url;

    @PostMapping("BuyGoodsSelect")
    public JSONObject BuyGoodsSelect(@RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        map.put("buyid", buyid);
        JSONObject json = buyGoodsInterface.BuyGoodsSelect(map);
        return json;
    }

    @PostMapping("BuyGoodsDan")
    public JSONObject BuyGoodsDan(String name, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        JSONObject json = buyGoodsInterface.BuyGoodsDan(buyid, name);
        return json;
    }

    @PostMapping("BuyGoodsInsert")
    public JSONObject BuyGoodsInsert(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "atlasfile[]", required = false) MultipartFile[] atlasfile, @RequestParam(value = "atlaspicfile", required = false) MultipartFile atlaspicfile, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);

        if (file != null) {
            JSONObject fjson = imgUtil.GoodsImg(url, file);
            map.put("img", fjson.toString());
        }

        if (atlasfile.length > 0) {
            JSONArray array = new JSONArray();
            for (MultipartFile file1 : atlasfile) {
                JSONObject fjson = imgUtil.GoodsImg(url, file1);
                array.add(fjson);
            }
            map.put("atlas", array.toString());
        }
        if (atlaspicfile != null) {
            JSONObject fjson = imgUtil.GoodsImgPic(url, atlaspicfile);
            map.put("atlaspic", fjson.toString());
        }
        JSONObject json = buyGoodsInterface.BuyGoodsInsert(map);
        if (json.getInteger("code") == 100) {
            RedisUpdate(map);
        }
        return json;
    }

    @PostMapping("BuyGoodsUpdate")
    public JSONObject BuyGoodsUpdate(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "atlasfile[]", required = false) MultipartFile[] atlasfile, @RequestParam(value = "atlaspicfile", required = false) MultipartFile atlaspicfile, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        if (file != null) {
            JSONObject fjson = imgUtil.GoodsImg(url, file);
            map.put("img", fjson.toString());
        }
        if (atlasfile.length > 0) {
            JSONArray array = new JSONArray();
            for (MultipartFile file1 : atlasfile) {
                JSONObject fjson = imgUtil.GoodsImg(url, file1);
                array.add(fjson);
            }
            map.put("atlas", array.toString());
        }
        if (atlaspicfile != null) {
            JSONObject fjson = imgUtil.GoodsImgPic(url, atlaspicfile);
            map.put("atlaspic", fjson.toString());
        }

        JSONObject json = buyGoodsInterface.BuyGoodsUpdate(map);
        if (json.getInteger("code") == 100) {
            RedisUpdate(map);
        }
        return json;
    }

    @PostMapping("BuyGoodsUpdateState")
    public JSONObject BuyGoodsUpdateState(@RequestParam("pid[]") String[] pid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        String sys_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("sys_userid", sys_userid);
        map.put("buyid", buyid);
        for (String id : pid) {
            map.put("pid", id);
            json = buyGoodsInterface.BuyGoodsUpdateState(map);
        }
        if (json.getInteger("code") == 100) {
            System.out.println(map);
            RedisUpdate(map);
        }
        return json;
    }

    public void RedisUpdate(Map<String, Object> map) {
        if (map.get("type").toString().equals("0")) {
            if (map.containsKey("classId")) {
                RedisUtils.DEL("class:" + map.get("buyid").toString() + ":" + map.get("classId").toString(), 6);
                RedisUtils.DEL("map:class:" + map.get("buyid").toString() + ":" + map.get("classId").toString(), 6);
            }
            RedisUtils.DEL("class:" + map.get("buyid").toString() + ":" + map.get("classid").toString(), 6);
            RedisUtils.DEL("map:class:" + map.get("buyid").toString() + ":" + map.get("classid").toString(), 6);
            RedisUtils.DEL("now:" + map.get("buyid").toString(), 6);
            RedisUtils.DEL("map:now:" + map.get("buyid").toString(), 6);

        } else if (map.get("type").toString().equals("1")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dt = sdf.format(new Date());
            String opentime = map.get("opentime").toString().substring(0, 10);
            System.out.println(opentime.equals(dt) + "--" + opentime + "===" + dt);
            if (opentime.equals(dt)) {
                List<String> list = RedisUtils.lrange("goods:" + map.get("buyid").toString() + ":" + dt, 0, -1, 0);
                RedisUtils.DEL("goods:" + map.get("buyid").toString() + ":" + dt, 0);
                for (String a : list) {
                    RedisUtils.DEL(map.get("buyid").toString() + ":" + a, 6);
                }
            }

        } else if (map.get("type").toString().equals("2")) {
            RedisUtils.DEL("jp:" + map.get("buyid").toString(), 6);
            RedisUtils.DEL("map:jp:" + map.get("buyid").toString(), 6);
        }
    }


}
