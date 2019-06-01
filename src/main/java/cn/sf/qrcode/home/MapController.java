package cn.sf.qrcode.home;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@RequestMapping("/qrcode/map")
public class MapController {


    

    @GetMapping("view")
    public String view( Model model) throws Exception {
    	
    
    	return "map";
    }
    
  
}
