package cn.sf.qrcode.code.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.qrcode.code.domain.CodeVO;
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

	@Override
	public List<CodeVO> findAllByOpenid(String openid) {
		Assert.notNull(openid, "code openId is null");
		final List<Code> list = repository.findByUserOpenid(openid);
		
		List<CodeVO> results = new ArrayList<>();
		
		for(Code entity : list) {
			
			CodeVO vo = new CodeVO();
		
			vo.setAlipayTimes(entity.getAlipayTimes());
			vo.setCreateTime(entity.getCreateTime());
			vo.setWeixinTimes(entity.getWeixinTimes());
			vo.setQqTimes(entity.getQqTimes());
			vo.setId(entity.getId());
			String name = entity.getName();
            if(name != null && !name.isEmpty()) {
                name = URLDecoder.decode(name);
            }
			vo.setName(name);
			results.add(vo);
		}
		return results;
	}

	@Override
	@Transactional
	public void deletedById(Long id) {
		Assert.notNull(id, "id is null");
		repository.deleteById(id);
		
	}

	
	
}
