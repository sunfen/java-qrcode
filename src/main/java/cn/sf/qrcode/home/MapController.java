package cn.sf.qrcode.home;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/09
 */
@Controller
@RequestMapping("/qrcode/map")
public class MapController {


    

    @GetMapping("view/{lat}/{lng}")
    public String view( Model model, @PathVariable Double lat, @PathVariable Double lng ) throws Exception {
    	model.addAttribute("lat", lat);
    	model.addAttribute("lng", lng);
    	return "map";
    }
    
    
    

    @GetMapping("view/auto/mp3")
    public String view( Model model) throws Exception {
    	
    	return "automp3";
    }
    
  
}
