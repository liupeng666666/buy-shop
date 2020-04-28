package com.whp.config.shrio;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.whp.config.jwt.JwtFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : 张吉伟
 * @data : 2018/5/15 14:30
 * @descrpition :
 */
@Configuration
public class ShiroConfiguration {
    @Value("${shiro.state}")
    private boolean ShiroState;

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        System.out.println("state:" + ShiroState);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        if (ShiroState) {
            Map<String, Filter> map = new HashMap<String, Filter>();
            map.put("jwt", new JwtFilter());
            shiroFilterFactoryBean.setFilters(map);
            shiroFilterFactoryBean.setUnauthorizedUrl("/User/UnLogin");
            Map<String, String> filterMap = new HashMap<String, String>();
            filterMap.put("/Auth/*", "anon");
            filterMap.put("/ueditor/*", "anon");
            filterMap.put("/wx/*/*", "anon");
            filterMap.put("/**", "jwt");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        } else {
            Map<String, String> filterMap = new HashMap<String, String>();
            filterMap.put("/**", "anon");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        }
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm2());
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    @Bean
    public MyRealm myRealm2() {
        return new MyRealm();
    }

}
