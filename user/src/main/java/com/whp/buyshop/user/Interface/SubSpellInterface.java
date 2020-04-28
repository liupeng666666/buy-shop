package com.whp.buyshop.user.Interface;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SubSpellInterface {
    JSONObject SubSpellSelect(Map<String, Object> map);

    JSONObject SubSpellUpdate(String[] spellid, Map<String, Object> map);
}
