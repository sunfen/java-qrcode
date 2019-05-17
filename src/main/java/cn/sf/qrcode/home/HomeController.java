package cn.sf.qrcode.home;

import java.math.BigDecimal;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;
import cn.sf.qrcode.common.QrCodeUtil;


@Controller
@RequestMapping("/qrcode/scan")
public class HomeController {
    
    private Log logger = LogFactory.getLog(HomeController.class);
    
    
    @Autowired
    private CodeRepository codeRepository;
    
    @GetMapping("image/{type}")
    @ResponseBody
    public void scanQQ(
    		@PathVariable String type,
    		@RequestParam String codeId, HttpServletResponse response) throws Exception {
    	
    	 final Code code = codeRepository.getOne(Long.valueOf(codeId));
         
         if(code == null) {
             logger.info("code is null : " + codeId);
             return ;
         }
         String url = code.getWx();
         if(type.equals("qq")) {
        	 url = code.getQq();
         }
        ImageIO.write(QrCodeUtil.createQrcode(url), "png", response.getOutputStream());
    }

    @GetMapping("view/{type}")
    public String scanQQ(
    		@PathVariable String type, 
    		@RequestParam String url,
    		@RequestParam(required = false) String name,  Model model) throws Exception {
    	
    	model.addAttribute("name", name);
    	model.addAttribute("type", type);
    	model.addAttribute("data", url);
    	return "view";
    }
    
    
    
    @SuppressWarnings("deprecation")
    @GetMapping("/{openid}/{codeId}")
    @ResponseBody
    public void scan(
        @PathVariable String openid,
        @PathVariable Long codeId,
        HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);
        
        if(code == null) {
            logger.info("code is null : " + codeId);
            return ;
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
            if(code.getWx() != null && !code.getWx().isEmpty()) {
        		response.sendRedirect("/qrcode/scan/view/wx?url=" + code.getId() + "&name=" + code.getName());
        	}
        } else if (agent.indexOf("alipayclient") > 0) {
            
            BigDecimal time = code.getAlipayTimes();
            if(time == null) {
            	time = new BigDecimal(0);
            }
            time = time.add(new BigDecimal(1));
            code.setAlipayTimes(time);
            codeRepository.save(code);
            
            response.sendRedirect(code.getAlipay());
            
        } else if (agent.indexOf("qq") > 0) {
        	
        	BigDecimal time = code.getQqTimes();
        	if(time == null) {
        		time = new BigDecimal(0);
        	}
        	time = time.add(new BigDecimal(1));
        	code.setQqTimes(time);
        	codeRepository.save(code);
        	if(code.getQq() != null && !code.getQq().isEmpty()) {
        		response.sendRedirect("/qrcode/scan/view/qq?url=" + code.getId() + "&name=" + code.getName());
        	}
        }else {
            logger.info(agent + " : " + code.getId());
        }
    }
  
}
