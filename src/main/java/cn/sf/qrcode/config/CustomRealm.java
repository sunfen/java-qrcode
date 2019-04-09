 package cn.sf.qrcode.config;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sf.qrcode.user.domain.entity.User;
import cn.sf.qrcode.user.service.UserService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/17
 */
public class CustomRealm extends AuthorizingRealm {
     
    private UserService userService;

     
    @Autowired
    private void setUserService(UserService userService) {
    
        this.userService = userService;
    }

     /**
      * 获取身份验证信息
      * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
      *
      * @param authenticationToken 用户身份信息 token
      * @return 返回封装了用户信息的 AuthenticationInfo 实例
      */
     @Override
     protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
         
         UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
  
         // 从数据库获取对应用户名密码的用户
         User user = userService.findByOpenid(token.getUsername());
         
         if (null == user) {
         
             throw new AccountException("用户名不正确");
         }
         else if (!user.getPassword().equals(new String((char[]) token.getCredentials()))) {
             throw new AccountException("密码不正确");
         }
         
         return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());
     }

     /**
      * 获取授权信息
      *
      * @param principalCollection
      * @return
      */
     @Override
     protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
         
       //  String username = (String) SecurityUtils.getSubject().getPrincipal();
    
         return new SimpleAuthorizationInfo();
     }
}
