 package cn.sf.qrcode.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.service.CodeService;
import cn.sf.qrcode.common.domain.HttpState;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@RequestMapping("/qrcode/code")
@ResponseBody
public class CodeController {

    @Autowired
    private CodeService codeService;
    
    
    /**
     * 
     * @param alipay
     * @param wx
     * @return
     */
    @PostMapping
    @ResponseBody
    public HttpState<Map<String, Object>> loginWechat(@RequestBody Code codeDTO) {
        
        final Code code = codeService.insert(codeDTO.getAlipay(), codeDTO.getWx());
        
        Map<String, Object> map = new HashMap<>();
        map.put("codeId", code.getId());

        return HttpState.success(map);
    }
}
