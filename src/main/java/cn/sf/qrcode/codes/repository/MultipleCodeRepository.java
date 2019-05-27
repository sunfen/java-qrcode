package cn.sf.qrcode.codes.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.qrcode.codes.domain.entity.MultipleCode;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface MultipleCodeRepository extends JpaRepository<MultipleCode, Long>{

    /**
     * @param codeId
     * @param userId
     */
    List<MultipleCode> findByUserId(Long userId);
}
