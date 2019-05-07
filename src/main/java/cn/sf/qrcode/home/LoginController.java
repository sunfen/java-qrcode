package cn.sf.qrcode.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.sf.qrcode.common.domain.HttpState;
import cn.sf.qrcode.user.domain.UserDTO;
import cn.sf.qrcode.user.domain.entity.User;
import cn.sf.qrcode.user.service.UserService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/qrcode/login")
@ResponseBody
public class LoginController {

    @Autowired
    private UserService userService;
    
    private static final String APPID = "wxf61bed124837fca5";
    private static final String SECRET = "ff8413faa5b46230fc4a2ed7d4981a69";

    private static final String APPID1 = "wxb7cb00126e90e1db";
    private static final String SECRET1 = "9e183418f7a8dac4b6a384cef4e89519";

    @Autowired
    private RestTemplate restTemplate;

    
    @GetMapping("session/{code}/{index}")
    public HttpState<Map<String, Object>> getAccessToken(@PathVariable String code,@PathVariable String index) throws Exception {
    	String appid = APPID;
    	String secret = SECRET;
    	
    	if( index.equals("1")) {
    		appid = APPID1;
    		secret = SECRET1;
    	}
        String accessTokenResult = 
            restTemplate.getForObject(String.format("https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&js_code=%s&appid=%s&secret=%s", 
            		code, appid, secret), String.class);
       
        Gson gson = new Gson();
        @SuppressWarnings("unchecked")
        Map<String, String> result = gson.fromJson(accessTokenResult, Map.class);
        
        if(result != null && result.get("openid") != null) {
        
            return this.login(new UserDTO(result.get("openid")));
        }
        
        return null;
    }
    
    
    
    
    /**
     * 登录
     * @param password
     * @param username
     * @return
     */
    @PostMapping
    @ResponseBody
    public HttpState<Map<String, Object>> loginWechat(@RequestBody UserDTO user) {
        
        return this.login(user);
    }
    
    
    
    private HttpState<Map<String, Object>> login(UserDTO user){
        final User entity = userService.insert(user.getOpenid());

        Map<String, Object> map = new HashMap<>();
        map.put("openid", entity.getOpenid());
        return HttpState.success(map);
    }
    

}
