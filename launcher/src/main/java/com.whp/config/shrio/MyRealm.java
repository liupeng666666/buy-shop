package com.whp.config.shrio;

import com.alibaba.fastjson.JSONObject;
import com.whp.buyshop.buy.Interface.SysBuyInterface;
import com.whp.buyshop.utils.util.JWTUtil;
import com.whp.config.jwt.JWTToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author : 张吉伟
 * @data : 2018/5/15 15:12
 * @descrpition : 前台用户授权信息，暂未提供权限。
 */
@Repository
public class MyRealm extends AuthorizingRealm {
    @Resource
    private SysBuyInterface sysBuyInterface;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String pid = JWTUtil.getUsername(principalCollection.toString(), "pid");
        if (pid == null) {
            throw new UnauthorizedException("token 不存在，请重新登陆");

        }
        JSONObject json = sysBuyInterface.SysBuySelect(pid);
        if (json.getInteger("code") != 100 || !json.containsKey("buy")) {
            throw new UnknownAccountException("用户不存在");
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String pid = JWTUtil.getUsername(token, "pid");
        if (pid == null) {
            throw new UnauthorizedException("token 不存在，请重新登陆");

        }
        JSONObject json = sysBuyInterface.SysBuySelect(pid);

        if (json.getInteger("code") != 100 || !json.containsKey("buy")) {
            throw new UnknownAccountException("用户不存在");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token, token, getName());
        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
}
