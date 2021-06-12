package com.lft.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple Quickstart application showing how to use Shiro's API.
 * @since 0.9 RC2
 */
public class Quickstart {
    
    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
    
    public static void main(String[] args) {
        
        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:
        // 创建具有配置领域、用户、角色和权限的 Shiro SecurityManager 的最简单方法是使用简单的 INI 配置。
        // 我们将通过使用一个可以摄取 .ini 文件并返回一个 SecurityManager 实例的工厂来做到这一点：
        
        
        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        
        
        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);
        
        // Now that a simple Shiro environment is set up, let's see what you can do:
        // get the currently executing user:
        // 获取当前的 执行的用户 subject 主题，通过 SecurityUtils.getSubject();
        Subject currentUser = SecurityUtils.getSubject();
        
        // Do some stuff with a Session (no need for a web or EJB container!!!)
        // 测试使用 Session
        // 获取 Session：通过 Subject$getSession()
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue"); // 设置 session 属性
        String value = (String) session.getAttribute("someKey"); // 获取 session 中对应属性名的值
        if (value.equals("aValue")) {
            log.error("Retrieved the correct value! [" + value + "]");
        }
        
        // let's login the current user so we can check against roles and permissions:
        // 测试当前的用户是否已经被认证，是否已经登录。
        // 调用 Subject$isAuthenticated() 方法
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装成一个 token 对象（UsernamePasswordToken类型）
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true); // 设置是否记住我。也就是记住用户名和密码。
            try {
                currentUser.login(token); // 调用 Subject$login 方法执行登录
            } catch (UnknownAccountException uae) { // 未知账户异常
                log.error("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) { // 错误凭证异常
                log.error("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) { // 账户锁定异常
                log.error("The account for username " + token.getPrincipal() + " is locked.  " +
                                  "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) { // 认证异常
                //unexpected condition?  error?
            }
        }
        
        //say who they are:
        //print their identifying principal (in this case, a username):
        // 调用 Subject$getPrincipal() 获取主体
        log.error("User [" + currentUser.getPrincipal() + "] logged in successfully.");
        
        //test a role:
        // 测试角色
        // 调用 Subject$hasRole() 判断是否包含有指定角色
        if (currentUser.hasRole("schwartz")) {
            log.error("May the Schwartz be with you!");
        } else {
            log.error("Hello, mere mortal.");
        }
        
        //test a typed permission (not instance-level)
        // 测试是否有某种行为的权限
        // 调用 Subject$isPermitted
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.error("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.error("Sorry, lightsaber rings are for schwartz masters only.");
        }
        
        //a (very powerful) Instance Level permission:
        // 测试是否有某种行为的权限
        // 调用 Subject$isPermitted
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.error("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                              "Here are the keys - have fun!");
        } else {
            log.error("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        
        //all done - log out!
        // 执行登出 调用 Subject$logout()
        currentUser.logout();
        
        System.exit(0);
    }
}
