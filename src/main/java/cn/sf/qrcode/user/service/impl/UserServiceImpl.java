package cn.sf.qrcode.user.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.sf.qrcode.user.domain.entity.User;
import cn.sf.qrcode.user.repository.UserRepository;
import cn.sf.qrcode.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    
    @Override
    public User insert(final String openId) {
        Assert.notNull(openId, "insert openId is null");
        
        User user = this.findByOpenid(openId);
        
        //insert user
        if(user == null) {
            user = new User();
            
            user.setOpenid(openId);
            user.setPassword(openId);
            user = repository.save(user);
        } 

       return user;
    }
    

    /** (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#findByOpenid(java.lang.String)
     */
    @Override
    public User findByOpenid(String openid) {
         
        Assert.notNull(openid, "findByOpenid openid is null");
         
        return repository.findByOpenid(openid);
    }


}
