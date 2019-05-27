package cn.sf.qrcode.codes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.sf.qrcode.codes.domain.entity.MultipleCode;
import cn.sf.qrcode.codes.repository.MultipleCodeRepository;
import cn.sf.qrcode.codes.service.MultipleCodeService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Service
public class MultipleCodeServiceImpl implements MultipleCodeService{

    @Autowired
    private MultipleCodeRepository repository;
    

	@Override
	public List<MultipleCode> findAllByUserId(Long userId) {
		
		Assert.notNull(userId, "code userId is null");

		return repository.findByUserId(userId);
	}

	
}
