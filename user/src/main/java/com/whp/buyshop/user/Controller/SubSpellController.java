package com.whp.buyshop.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Interface.SubSpellInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.buyshop.utils.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/SubSpell")
public class SubSpellController {
    @Autowired
    private SubSpellInterface subSpellInterface;

    @PostMapping("SubSpellSelect")
    public JSONObject SubSpellSelect(@RequestParam Map<String, Object> map) {
        JSONObject json = subSpellInterface.SubSpellSelect(map);
        return json;
    }

    @PostMapping("SubSpellUpdate")
    public JSONObject SubSpellUpdate(@RequestParam("spellid[]") String[] spellid, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        String refund_userid = JWTUtil.getUsername(request.getHeader("Authorization"), "pid");
        map.put("refund_userid", refund_userid);
        SnowFlake snowFlake = new SnowFlake(1, 1);
        map.put("pid", snowFlake.nextId());
        JSONObject json = subSpellInterface.SubSpellUpdate(spellid, map);

        return json;
    }

}
