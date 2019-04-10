package cn.sf.qrcode.home;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;
import cn.sf.qrcode.common.QrCodeUtil;


@Controller
@RequestMapping("/qrcode/scan")
@ResponseBody
public class HomeController {
    
    private static Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CodeRepository codeRepository;
    
    @GetMapping("/{openid}/{codeId}")
    public void scan(
        @PathVariable String openid,
        @PathVariable Long codeId,
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);

        String agent = request.getHeader("User-Agent").toLowerCase();
        
        if (agent.indexOf("micromessenger") > 0) {
            
            QrCodeUtil.encode(code.getName(), "F:\\qrcode.jpg", response.getOutputStream());
        } else if (agent.indexOf("alipayclient") > 0) {
            
            log.info("阿里支付");
            URI url = new URI(code.getAlipay());
            restTemplate.postForLocation(url, request);
        }else {
            
            QrCodeUtil.encode(code.getWx(), code.getName(), response.getOutputStream());
        }
    }
    

    
    
    
    
    public String aliPay() throws AlipayApiException {
        
        AlipayClient alipayClient = new DefaultAlipayClient(
                "openapi", "appid","rsa_private_key",  "json", "utf-8", "alipay_pub_key");
        
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
        alipayRequest.setReturnUrl("http://192.168.1.219:22222/ali.html");
        
        alipayRequest.setNotifyUrl("http://192.168.1.219:22222/callback");// 在公共参数中设置回跳和通知地址
        
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"20160320020192222\"," + "    \"total_amount\":66.66,"
                + "    \"subject\":\"主题\"," + "    \"seller_id\":\"213215dsa\"," +
                // " \"product_code\":\"QUICK_WAP_PAY\"" +
                "  }");// 填充业务参数
        
        String form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
 
        return form;
    }
}
