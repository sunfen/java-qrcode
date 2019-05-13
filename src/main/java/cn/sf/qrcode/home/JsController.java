package cn.sf.qrcode.home;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.sf.qrcode.common.QrCodeUtil;
import cn.sf.qrcode.common.WeChatUtils;
import cn.sf.qrcode.webview.domain.ViewVO;
import cn.sf.qrcode.webview.domain.WxSignatureVO;


/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@RequestMapping("/qrcode/webview")
public class JsController {


    private static final String TOKEN_KEY = "webviewtoken";
    private static final String TICKET_KEY = "getticket";    
    private static final String APPID = "wx8d81dbdeeb1aab39";
    private static final String SECRET = "01a7644ff804fddb6adf1f40e94c3b87";
    private static final String NONCESTR = "sunfen";
    private static final String FORMAT = "PNG";

    @Value("${qrcode.url}")
    private String Base_Path;
    
    @Value("${qrcode.file.upload-path}")
    private String fahterPath;
    
    
    @Autowired
    private RestTemplate restTemplate;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
    
	Gson gson = new Gson();
    /**
     * 
     * @return
     */
	@RequestMapping("MP_verify_w6oQo78ixg7aKQsK.txt")
    @ResponseBody
    public String js() {
        return "w6oQo78ixg7aKQsK";
    }
	
	@GetMapping("")
	public String view(Model  model, @RequestParam(required = false) String alipay,
			@RequestParam(required = false) String alipayShow,
			@RequestParam(required = false) String person,
			@RequestParam(required = false) String personShow,
			@RequestParam(required = false) String name) throws Exception {
		
		ViewVO vo = new ViewVO();
		
		vo.setName(name);
		vo.setAlipay(alipay);
		vo.setAlipayShow(alipayShow);

		vo.setPerson(person);
		vo.setPersonShow(personShow);
		
		Boolean isShowAlipay = false;
		Boolean isShowPerson = false;
		
		if(alipay != null && !alipay.isEmpty() && alipayShow  != null && alipayShow.equals("true")) {
			isShowAlipay= true;
		}
		
		if(person != null && !person.isEmpty() && personShow  != null && personShow.equals("true")) {
			isShowPerson= true;
		}
		
		vo.setIsShowAlipay(isShowAlipay);
		vo.setIsShowPerson(isShowPerson);
		
		model.addAttribute("data", vo);
		
		return "view";
	}
	
	@GetMapping("image")
	public void showImage(@RequestParam String url,  HttpServletResponse response) throws Exception {
		BufferedImage inputStream = QrCodeUtil.createQrcode(url);     
        ImageIO.write(inputStream, FORMAT, response.getOutputStream());
	}
	
	
	
	
	
	
	 @GetMapping("token")
	 @ResponseBody
	 @SuppressWarnings("unchecked")
	 public WxSignatureVO getAccessToken(@RequestParam String htmlUrl) throws Exception {
		 		
		String token =  (String)redisTemplate.opsForValue().get(TOKEN_KEY);
		 if(token == null) {
			 
			 String accessTokenResult = 
					 restTemplate.getForObject(String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID, SECRET), String.class);
			 
			 final Map<String, String> result = gson.fromJson(accessTokenResult, Map.class);
			 
			 if(result != null && result.get("access_token") != null) {
				 token = result.get("access_token");
				 redisTemplate.opsForValue().set(TOKEN_KEY,token, 7200, TimeUnit.SECONDS);
				
			 }
		 }
		 
		 String ticket = (String)redisTemplate.opsForValue().get(TICKET_KEY);
	     if(ticket == null) {
			 String accessTokenResult = 
					 restTemplate.getForObject(
							 String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", token), String.class);
			 
			 final Map<String, String> result = gson.fromJson(accessTokenResult, Map.class);
			 
			 if(result != null && result.get("ticket") != null) {
				 ticket = result.get("ticket");
				 redisTemplate.opsForValue().set(TICKET_KEY,ticket, 7200, TimeUnit.SECONDS);
				
			 }
	     }
	     
	     
	     	WxSignatureVO signatureDto = new WxSignatureVO();
	        String timestamp = (System.currentTimeMillis() / 1000) + "";
	        signatureDto.setTimestamp(timestamp);
	        signatureDto.setAppid(APPID);
	        signatureDto.setNoncestr(NONCESTR);
	        signatureDto.setSignature(
	        		WeChatUtils.getSignature(timestamp, NONCESTR, htmlUrl, ticket));
				 
	        return signatureDto;
	    }
}
