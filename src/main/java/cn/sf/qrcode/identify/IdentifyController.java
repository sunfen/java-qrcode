package cn.sf.qrcode.identify;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.imageclassify.AipImageClassify;


@Controller
@RequestMapping("/qrcode/identify")
public class IdentifyController {

	private static String access_token = "identify_access_token";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
	
    private static String APP_ID = "16432049";
	private static String API_KEY = "ofdrWFzN22Yp9ZwBd9HQY4AN";
	private static String SECRET_KEY = "K6RQnEHnF628HTXSYnkP64wZA32QqRy1";
	
    AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
    
	@PostMapping("/{type}")
	@ResponseBody
	public String identify(MultipartFile files, @PathVariable String type) throws IOException {
		 // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("baike_num", "5");
	    
		// 参数为二进制数组
		final byte[] imgData = files.getBytes();
		
	    JSONObject res = null;
		switch (type) {
	    	//通用物体
		    case "common":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    case "dish":
		    	res = client.advancedGeneral(imgData, options);
				break;
		    case "car":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    case "logo":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    case "animal":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    	//植物
		    case "plant":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    	//图像主体	
		    case "object":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    	//地标
		    case "land":
		    	res = client.advancedGeneral(imgData, options);
		    	break;
		    default:
				break;
		}
	    if(res != null) {
	    	return res.toString();
	    }
	    
	    String accessToken = redisTemplate.opsForValue().get(access_token);
		if(accessToken == null || accessToken.isEmpty() ) {
			accessToken = AuthService.getAuth();
			redisTemplate.opsForValue().set(access_token, accessToken);
		}
        
		try {
        	final String imgStr = Base64Util.encode(imgData);
        	final String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        	final String param = "image=" + imgParam;
        	//final String param1 = "baike_num=" + 5;
        	final String url = this.getUrl(type);
        	
            return HttpUtil.post(url, accessToken, param);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;

	}
	
	
	private String getUrl(String type) {
	    
	    String url = null;
	   
	    switch (type) {
		    	//花卉
		    case "flower":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/flower";
		    	break;
		    	//果蔬类食材识别
		    case "ingredient":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
		    	break;
		    	//地标
		    case "land":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/landmark";
		    	break;
		    
		    	//红酒
		    case "redwine":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/redwine";
		    	break;
		    	
		    	//货币
		    case "currency":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/currency";
		    	break;
		    	
		    	//车辆属性
		    case "vehicle_attr":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/vehicle_attr";
		    	break;
		    
		    	//车辆外观损伤识别
		    case "vehicle_damage":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/vehicle_damage";
		    	break;
		
		    default:
				break;
		}
	    
	    return url;
	}

}
