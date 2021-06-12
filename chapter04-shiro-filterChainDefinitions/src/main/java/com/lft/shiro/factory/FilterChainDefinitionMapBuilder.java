package com.lft.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
    
    public LinkedHashMap<String, String> builderFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //         <!--        /login.jsp = anon-->
        //         <!--        /shiro/login = anon-->
        //         <!--        /shiro/logout = logout-->
        //
        //         <!--        /user.jsp = roles[user]-->
        //         <!--        /admin.jsp = roles[admin]-->
        map.put("/login.jsp", "anon");
        map.put("/shiro/login", "anon");
        map.put("/shiro/logout", "logout");
        
        map.put("/user.jsp", "roles[user]");
        map.put("/admin.jsp", "roles[admin]");
        
        map.put("/**", "authc");
        return map;
    }
}
