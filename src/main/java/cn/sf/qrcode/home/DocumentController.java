package cn.sf.qrcode.home;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sf.qrcode.common.domain.HttpState;
import cn.sf.qrcode.document.domain.DocumentVO;
import cn.sf.qrcode.document.service.DocumentService;
import cn.sf.qrcode.user.domain.entity.User;
import cn.sf.qrcode.user.service.UserService;


@Controller
@RequestMapping("/qrcode/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseBody
	public HttpState<Map<String, Object>>  save(MultipartFile files, String objectId) throws IOException {
		User user = userService.findByOpenid(objectId);
		if(user == null) {
			return null;
		}
		final DocumentVO document = documentService.insert(files.getInputStream(), files.getOriginalFilename(), user.getId());
        
		Map<String, Object> map = new HashMap<>();
       
        if(document != null) {
        	map.put("path", document.getPath());
        }
        return HttpState.success(map);
	}

}
