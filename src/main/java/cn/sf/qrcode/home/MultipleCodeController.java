package cn.sf.qrcode.home;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.qrcode.codes.domain.entity.MultipleCode;
import cn.sf.qrcode.codes.repository.MultipleCodeRepository;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@ResponseBody
@RequestMapping("/qrcode/multiple/scan")
public class MultipleCodeController {
    
    private Log logger = LogFactory.getLog(HomeController.class);
    
    
    @Autowired
    private MultipleCodeRepository codeRepository;
    
    @GetMapping("/{userId}")
    @ResponseBody
    public void scan(
    		@PathVariable Long userId, HttpServletResponse response) throws Exception {
    	
    	final List<MultipleCode> codes = codeRepository.findByUserId(userId);
         
         
    	if(codes == null || codes.isEmpty()) {
        
    		logger.info("code is null : " + userId);
            return ;
    	}
        
    	final int length = codes.size();
    	Random random = new Random();
    	
    	final int index = random.nextInt(length);
    	final MultipleCode code = codes.get(index);
    	
    	if(code == null || code.getUrl() == null) {
    		return;
    	}
    	
        BigDecimal time = code.getTimes();
        if(time == null) {
        	time = new BigDecimal(0);
        }
        time = time.add(new BigDecimal(1));
    	code.setTimes(time);
    	
    	
    	response.sendRedirect(code.getUrl());
    }
}
