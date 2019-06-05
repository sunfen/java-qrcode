package cn.sf.qrcode.identify;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/qrcode/identify")
public class IdentifyController {

	private static String access_token = "identify_access_token";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
	
	@PostMapping("/{type}")
	@ResponseBody
	public String identify(MultipartFile files, @PathVariable String type) throws IOException {
		String accessToken = redisTemplate.opsForValue().get(access_token);
		if(accessToken == null || accessToken.isEmpty() ) {
			accessToken = AuthService.getAuth();
			redisTemplate.opsForValue().set(access_token, accessToken);
		}
	    
        try {
        	// 参数为二进制数组
        	final byte[] imgData = files.getBytes();
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
	    	//通用物体
		    case "common":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
		    	break;
	
		    case "dish":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
				break;

		    case "car":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/car";
		    	break;
		    	
		    case "logo":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/logo";
		    	break;
		    	
		    case "animal":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/animal";
		    	break;
		    	//植物
		    case "plant":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
		    	break;
		    	//花卉
		    case "flower":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/flower";
		    	break;
		    	//果蔬类食材识别
		    case "ingredient":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/classify/ingredient";
		    	break;
		    
		    	//图像主体	
		    case "object":
		    	url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/object_detect";
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
