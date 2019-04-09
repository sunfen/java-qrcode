 package cn.sf.qrcode.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.qrcode.user.domain.entity.User;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface UserRepository extends JpaRepository<User, Long>{

    User findByOpenid(String openid);

    /**
     * @param username
     * @return
     */
    User findByOpenidOrPhone(String openid, String phone);
}
