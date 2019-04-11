package cn.sf.qrcode.code.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.qrcode.code.domain.entity.Code;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface CodeRepository extends JpaRepository<Code, Long>{

    Code findByAlipayAndWx(String alipay, String wx);

    /**
     * @param codeId
     * @param openid
     */
    Code findByIdAndUserOpenid(Long codeId, String openid);
}
