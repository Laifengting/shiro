package com.lft.shiro.service;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ShiroService {
    
    @RequiresRoles (value = {"admin"})
    public void testMethod() {
        System.out.println(new Date());
    }
    
}
