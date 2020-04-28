package com.whp.buyshop.user.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.user.Interface.SubMumberRecruitInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 张吉伟
 * @data : 2019/9/11 19:23
 * @descrpition :
 */
@RestController
@RequestMapping("SubMumberRecruit")
public class SubMumberRecruitController {
    @Autowired
    private SubMumberRecruitInterface subMumberRecruitInterface;

    @PostMapping("SubMumberRecruitSelect")
    public JSONObject SubMumberRecruitSelect(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String buyid = JWTUtil.getUsername(request.getHeader("Authorization"), "buyid");
        json = subMumberRecruitInterface.SubMumberRecruitSelect(buyid);
        return json;
    }
}
