package cn.sf.qrcode.code.service;

import cn.sf.qrcode.code.domain.entity.Code;

public interface CodeService {


    /**
     * @param alipay
     * @param wx
     * @return
     */
    Code insert(String alipay, String wx, String name, String openId, String qq);

 
}
