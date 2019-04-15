package cn.sf.qrcode.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    @Autowired
    private CodeRepository codeRepository;
    
    
    @GetMapping("/{openid}/{codeId}")
    public void scan(
        @PathVariable String openid,
        @PathVariable Long codeId,
        HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);

        String agent = request.getHeader("User-Agent").toLowerCase();
        
        if (agent.indexOf("micromessenger") > 0) {
            
            QrCodeUtil.encode(code.getWx(), code.getName(), response.getOutputStream());
        } else if (agent.indexOf("alipayclient") > 0) {
            
            response.sendRedirect(code.getAlipay());
        }else {
            response.sendRedirect(code.getAlipay());
        }
    }
  
}
