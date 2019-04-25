package cn.sf.qrcode.user.service;

import cn.sf.qrcode.user.domain.entity.User;

public interface UserService {

    /**
     * 查询
     * @param openid
     */
    User findByOpenid(String openid);

    /**
     * @param username
     * @return
     */
    User insert(String openid);

}
