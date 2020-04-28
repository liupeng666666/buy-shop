package com.whp.buyshop.wx.Impl;


import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.wx.Dao.WxMenuDao;
import com.whp.buyshop.wx.Interface.WxMenuInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxMenuImpl implements WxMenuInterface {
    @Autowired
    private WxMenuDao wxMenuDao;

    @Override
    public JSONObject WxMenuInsert(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = wxMenuDao.getBuyById(map);
            if (map1.size() > 0) {
                map.put("appid", map1.get("appid"));
                map.put("wechat_appid", map1.get("wechat_appid"));
                wxMenuDao.WxMenuInsert(map);
                json.put("code", 100);
            } else {
                json.put("code", 103);
            }

        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMenuSelect(Map<String, Object> map, int page, int num) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMenuDao.WxMenuSelectByPage(map, page, num);
            int count = wxMenuDao.WxMenuSelectByCount(map);
            json.put("code", 100);
            json.put("list", list);
            json.put("count", count);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMenuUpdate(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            if (map.get("type").toString().equals("0")) {
                map.put("key", "");
                map.put("pagepath", "");
            } else if (map.get("type").toString().equals("1")) {
                map.put("url", "");
                map.put("pagepath", "");
            } else {
                map.put("url", "www.whpck.com/");
                map.put("key", "");
                map.put("pagepath", "/pages/home/home");
            }
            wxMenuDao.WxMenuUpdate(map);
            json.put("code", 100);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMenuUpdateState(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            Map<String, Object> map1 = new HashMap();
            List<Map<String, Object>> plist = new ArrayList<>();
            List<Map<String, Object>> mlist = new ArrayList<>();
            List<Map<String, Object>> list = wxMenuDao.getMenuById(map.get("pid").toString());
            for (Map mmap : list) {
                plist = wxMenuDao.getMenuById(mmap.get("parentid").toString());
            }
            mlist = menuDigui(mlist, map.get("pid").toString());

            if (map.containsKey("state")) {//启用禁用
                if (map.get("state").toString().equals("0")) {
                    map1.put("state", "0");
                    if (plist == null || plist.size() == 0) {
                        map1.put("pid", map.get("pid").toString());
                        wxMenuDao.WxMenuUpdateState(map1);
                        json.put("code", 100);
                    }
                    for (Map pmap : plist) {
                        if (pmap.get("state").toString().equals("0")) {
                            map1.put("pid", map.get("pid").toString());
                            wxMenuDao.WxMenuUpdateState(map1);
                            json.put("code", 100);
                        } else {
                            json.put("code", 104);
                            json.put("message", "请先开启父级");
                        }
                    }

                } else {
                    map1.put("state", "1");
                    map1.put("pid", map.get("pid").toString());
                    wxMenuDao.WxMenuUpdateState(map1);
                    if (mlist != null || mlist.size() > 0) {
                        for (Map m : mlist) {
                            map1.put("pid", m.get("pid").toString());
                            wxMenuDao.WxMenuUpdateState(map1);
                        }
                    }
                    json.put("code", 100);
                }

            }
            if (map.containsKey("isdel")) {//删除
                if (map.get("isdel").toString().equals("1")) {
                    map1.put("isdel", "1");
                    map1.put("pid", map.get("pid").toString());
                    wxMenuDao.WxMenuUpdateState(map1);
                    if (mlist != null || mlist.size() > 0) {
                        for (Map m : mlist) {
                            map1.put("pid", m.get("pid").toString());
                            wxMenuDao.WxMenuUpdateState(map1);
                        }
                    }
                    json.put("code", 100);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMenuSelectmenu(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMenuDao.WxMenuSelectmenu(map);
            json.put("code", 100);
            json.put("menu", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    @Override
    public JSONObject WxMenuSelectById(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        try {
            List<Map<String, Object>> list = wxMenuDao.WxMenuSelectById(map);
            json.put("code", 100);
            json.put("menu", list);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("code", 103);
        }
        return json;
    }

    public List<Map<String, Object>> menuDigui(List<Map<String, Object>> mlist, String pid) {
        List<Map<String, Object>> list = wxMenuDao.getMenuByPid(pid);
        for (Map<String, Object> cmap : list) {
            mlist.add(cmap);
            menuDigui(mlist, cmap.get("pid").toString());
        }
        return mlist;
    }
}
