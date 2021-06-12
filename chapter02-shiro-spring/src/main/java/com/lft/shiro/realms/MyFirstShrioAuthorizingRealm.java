package com.lft.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyFirstShrioAuthorizingRealm extends AuthorizingRealm {
    
    /**
     * 用于授权的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("MyFirstShrioAuthorizingRealm 的 doGetAuthorizationInfo...执行了。。。");
        // Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        // return new SimpleAccount();
        return null;
        
    }
    
    /**
     * 用于认证的访求
     */
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("[FirstRealm] 执行了 doGetAuthenticationInfo 方法，传入的 token 为：" + token);
        // 1. 把传入的 AuthenticationToken 类型的 token 对象转换为 UsernamePasswordToken 类型
        UsernamePasswordToken tokenUsernamePassword = (UsernamePasswordToken) token;
        // 2. 从 UsernamePasswordToken 对象中获取 username 和 password
        String username = tokenUsernamePassword.getUsername();
        String password = new String(tokenUsernamePassword.getPassword());
        
        // 3. 调用数据库的方法，从数据库中查询 username 对应的用户记录
        System.out.println("从数据库中获取用户记录：" + username + " 所对应的用户信息。");
        
        // 4. 若用户不存在，则可以抛出 UnknowAccountException 异常。
        if ("unknown".equals(username)) {
            throw new UnknownAccountException("用户不存在");
        }
        
        // 5. 根据用户信息的情况，决定是否抛出其他 AuthenticationException 异常
        if ("monster".equals(username)) {
            throw new LockedAccountException("账户被锁定异常");
        }
        
        // 6. 根据用户信息，构建 AuthenticationInfo 对象并返回，通常的实现类是 SimpleAuthenticationInfo
        // 以下信息是从数据库中获取的。
        // ① principal：认证的实体信息。可以是 username，也可以是数据表对应的用户的实体类对象。
        Object principal = username;
        // ② credentials：数据库中获取的密码
        Object credentials = null;
        if ("admin".equals(username)) {
            credentials = "928bfd2577490322a6e19b793691467e";
        } else if ("user".equals(username)) {
            credentials = "b8c2d5b0a37cc51f91d5e8970347a3a3";
        }
        // ③ credentialsSalt：加密盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        // ④ realmName：当前 Realm 对象的 name。调用 父类的 getName() 方法即可获取。
        String realmName = getName();
        
        // SimpleAuthenticationInfo 用于封装从数据库或者缓存中查询到的用户信息。
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }
}
