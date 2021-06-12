package com.lft.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping ("shiro")
public class ShiroController {
    
    @PostMapping ("login")
    public String login(@RequestParam ("username") String username, @RequestParam ("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        
        // 测试当前的用户是否已经被认证，是否已经登录。
        // 调用 Subject$isAuthenticated() 方法
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装成一个 token 对象（UsernamePasswordToken类型）
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // token.setRememberMe(true); // 设置是否记住我。也就是记住用户名和密码。
            try {
                currentUser.login(token); // 调用 Subject$login 方法执行登录
            } catch (AuthenticationException ae) { // 认证异常
                System.out.println("登录失败" + ae.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}
