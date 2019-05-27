package cn.sf.qrcode.codes.service;

import java.util.List;

import cn.sf.qrcode.codes.domain.entity.MultipleCode;

public interface MultipleCodeService {

    List<MultipleCode> findAllByUserId(Long userId);
 
}
