package cn.sf.qrcode.home;



import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/qrcode/picture")
public class PictureController {

	    
	@Value("${qrcode.file.view}")
	private String FILE_Path;
	
	
    @GetMapping("/{src}/{title}")
    public String scan(
    		Model model,
    		@PathVariable String src, 
    		@PathVariable String title) throws Exception {
    	
    	
    	model.addAttribute("src", FILE_Path + src);
    	model.addAttribute("title", title);
    	
    	return "picture";
    }
    
  
}
