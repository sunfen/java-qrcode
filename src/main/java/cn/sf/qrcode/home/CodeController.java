package cn.sf.qrcode.home;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.qrcode.code.domain.CodeVO;
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
    @SuppressWarnings("deprecation")
    @PostMapping
    @ResponseBody
    public HttpState<Map<String, Object>> loginWechat(@RequestBody CodeVO codeDTO) {
        String name = codeDTO.getName();
        
        if(name != null && !name.isEmpty()) {
            name = URLEncoder.encode(name.trim());
        }
        
        final Code code = codeService.insert(codeDTO.getAlipay(), codeDTO.getWx(), name, codeDTO.getOpenId(), codeDTO.getQq());
        
        Map<String, Object> map = new HashMap<>();
        map.put("codeId", code.getId());

        return HttpState.success(map);
    }
    
    
	@GetMapping("{openid}")
	@ResponseBody
	public List<CodeVO> getAll(@PathVariable("openid") String openid) throws IOException {
		if(openid == null) {
			return null;
		}
		
		return codeService.findAllByOpenid(openid);
	}

	
	@DeleteMapping("{id}")
	@ResponseBody
	public void getAll(@PathVariable("id") Long id) throws IOException {
		
		if(id == null) {
			return;
		}
		
		codeService.deletedById(id);
	}
}
