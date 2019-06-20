package cn.sf.qrcode.home;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sf.qrcode.codes.domain.entity.MultipleCode;
import cn.sf.qrcode.codes.repository.MultipleCodeRepository;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@RequestMapping("/qrcode/multiple/scan")
public class MultipleCodeController {
    
    private Log logger = LogFactory.getLog(HomeController.class);
    
    @Autowired
    private MultipleCodeRepository codeRepository;
    
    @GetMapping("/{userId}/{type}")
    public String scan(
    		Model model,
    		@PathVariable Long userId, 
    		@PathVariable Integer type,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	final MultipleCode code = codeRepository.getOne(userId);
         
    	if(code == null ) {
        
    		logger.info("multiple code is null : " + userId);
    	}
    	
    	//type 1 观看视频      2 查看次数
    	if(type  == 1) {
    		BigDecimal time = code.getTimes();
    		if(time == null) {
    			time = new BigDecimal(0);
    		}
    		time = time.add(new BigDecimal(1));
    		code.setTimes(time);
    		codeRepository.save(code);
    	}
    	
    	model.addAttribute("url", code.getUrl());
    	
    	model.addAttribute("times", code.getTimes() == null ? 0 : code.getTimes().intValue());
    	model.addAttribute("type", type);
    	return "automp4";
    }
}
