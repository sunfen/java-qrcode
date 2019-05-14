package cn.sf.qrcode.code.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;
import cn.sf.qrcode.code.service.CodeService;
import cn.sf.qrcode.user.service.UserService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Service
public class CodeServiceImpl implements CodeService{


    @Autowired
    private UserService userService;
    @Autowired
    private CodeRepository repository;
    
    /* (non-Javadoc)
     * @see cn.sf.qrcode.code.service.CodeService#insert(java.lang.String, java.lang.String)
     */
    @Override
    @Transactional
    public Code insert(String alipay, String wx, String name, String openId, String qq) {
        Assert.notNull(openId, "code openId is null");
        Code code = new Code();
        
        code.setUser(userService.insert(openId));
        code.setQq(qq);
        code.setWx(wx);
        code.setAlipay(alipay);
        code.setName(name);
        return repository.save(code);
    }

}
