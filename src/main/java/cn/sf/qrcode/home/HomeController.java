package cn.sf.qrcode.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;


@Controller
@RequestMapping("/qrcode/scan")
@ResponseBody
public class HomeController {
    
    private static Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private CodeRepository codeRepository;
    
    @GetMapping("/{openid}/{codeId}")
    public void scan(
        @PathVariable String openid,
        @PathVariable String codeId) {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);
        if(code == null) {
            log.error("code is null");
        }else {
            log.info("code wx : {}, alipay : {}", code.getWx(), code.getAlipay());
        }
    }

}
