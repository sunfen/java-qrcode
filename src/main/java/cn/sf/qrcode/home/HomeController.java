package cn.sf.qrcode.home;

import java.math.BigDecimal;
import java.net.URLDecoder;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;
import cn.sf.qrcode.common.QrCodeUtil;


@Controller
@RequestMapping("/qrcode/scan")
@ResponseBody
public class HomeController {
    
    private Log logger = LogFactory.getLog(HomeController.class);
    
    
    @Autowired
    private CodeRepository codeRepository;
    
    
    @SuppressWarnings("deprecation")
    @GetMapping("/{openid}/{codeId}")
    public void scan(
        @PathVariable String openid,
        @PathVariable Long codeId,
        HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);
        
        if(code == null) {
            logger.info("code is null : " + codeId);
            return;
        }
        
        final String agent = request.getHeader("User-Agent").toLowerCase();
        
        if (agent.indexOf("micromessenger") > 0) {
            
        	String name = code.getName();
            if(name != null && !name.isEmpty()) {
                name = URLDecoder.decode(name);
            }
            BigDecimal time = code.getWeixinTimes();
            if(time == null) {
            	time = new BigDecimal(0);
            }
            time = time.add(new BigDecimal(1));
            
            code.setWeixinTimes(time);
            codeRepository.save(code);
            
            QrCodeUtil.encode(code.getWx(), name, response.getOutputStream());
            
        } else if (agent.indexOf("alipayclient") > 0) {
            
            BigDecimal time = code.getAlipayTimes();
            if(time == null) {
            	time = new BigDecimal(0);
            }
            time = time.add(new BigDecimal(1));
            code.setAlipayTimes(time);
            codeRepository.save(code);
            
            response.sendRedirect(code.getAlipay());
            
        }else {
            logger.info(agent + " : " + code.getId());
        }
    }
  
}
