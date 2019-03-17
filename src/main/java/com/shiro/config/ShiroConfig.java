package com.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.shiro.pojo.Menu;
import com.shiro.service.ShiroService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    @Autowired
    ShiroService shiroService;
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("roleOrFilter",new RoleOrFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("/login","anon");
        map.put("/logout","logout");
        map.put("/","anon");
        List<Menu> menus = shiroService.patterns();
        for (Menu menu : menus){
            List<String> roles = shiroService.roles(menu.getId());
            StringBuffer buffer = new StringBuffer("roleOrFilter[");
            for (int i = 0; i < roles.size();i++){
                if(i==0){
                    buffer.append(roles.get(i));
                }else{
                    buffer.append(","+roles.get(i));
                }
            }
            buffer.append("]");
            map.put(menu.getPattern(),buffer.toString());
        }
        /*map.put("/level1/**","roleOrFilter[user,admin,root]");
        map.put("/level2/**","roleOrFilter[admin,root]");
        map.put("/level3/**","roles[root]");*/
        map.put("/**","authc");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
