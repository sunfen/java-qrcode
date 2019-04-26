package cn.sf.qrcode.home;

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
        
        String agent = request.getHeader("User-Agent").toLowerCase();
        
        if (agent.indexOf("micromessenger") > 0) {
            String name = code.getName();
            if(name != null && !name.isEmpty()) {
                name = URLDecoder.decode(name);
            }
            QrCodeUtil.encode(code.getWx(), name, response.getOutputStream());
        } else if (agent.indexOf("alipayclient") > 0) {
            
            response.sendRedirect(code.getAlipay());
        }else {
            logger.info(agent + " : " + code.getId());
        }
    }
  
}
