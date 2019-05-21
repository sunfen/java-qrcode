package cn.sf.qrcode.code.service;

import java.util.List;

import cn.sf.qrcode.code.domain.CodeVO;
import cn.sf.qrcode.code.domain.entity.Code;

public interface CodeService {


    /**
     * @param alipay
     * @param wx
     * @return
     */
    Code insert(String alipay, String wx, String name, String openId, String qq);

    List<CodeVO> findAllByOpenid(String openid);
    
    void deletedById(Long id);
 
}
