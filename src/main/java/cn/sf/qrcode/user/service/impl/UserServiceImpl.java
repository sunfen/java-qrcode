package cn.sf.qrcode.user.service.impl;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.qrcode.user.domain.entity.User;
import cn.sf.qrcode.user.domain.UserDTO;
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
    public User insert(final UserDTO userDTO) {
        Assert.notNull(userDTO, "userDTO is null");
        Assert.notNull(userDTO.getOpenid(), "userDTO is null");

        User user = this.findByOpenid(userDTO.getOpenid());
        
        //insert user
        if(user == null) {
           user = this.insertByWechat(userDTO);
        }else if(userDTO.getUsername() != null) {
            user.setAvatarUrl(userDTO.getAvatarUrl());
            user.setUsername(userDTO.getUsername());
            user = repository.save(user);
        }

       return user;
    }
    
    
    /** (non-Javadoc)
     * @return 
     * @see cn.sf.sculpture.user.service.UserService#insertByWechat(cn.sf.sculpture.user.domain.UserDTO)
     */
    @Transactional
    public User insertByWechat(UserDTO user) {
        Assert.notNull(user, "user is null");
        
        User entity = new User();
        
        entity.setOpenid(user.getOpenid());
        entity.setUsername(user.getUsername());
        entity.setAvatarUrl(user.getAvatarUrl());
        entity.setPassword(user.getOpenid());
        
        return repository.save(entity);
         
    }

    /** (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#findByOpenid(java.lang.String)
     */
    @Override
    public User findByOpenid(String openid) {
         
        Assert.notNull(openid, "findByOpenid openid is null");
         
        return repository.findByOpenid(openid);
    }
    
    
    /** (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#findByOpenid(java.lang.String)
     */
    @Override
    public User findCurrentUser() {

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        String openId = (String)principal;
        return repository.findByOpenid(openId);
    }


}
