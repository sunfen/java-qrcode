package cn.sf.qrcode.home;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.google.gson.Gson;

import cn.sf.qrcode.code.domain.entity.Code;
import cn.sf.qrcode.code.repository.CodeRepository;
import cn.sf.qrcode.common.AlipayConfig;
import cn.sf.qrcode.common.QrCodeUtil;


@Controller
@RequestMapping("/qrcode/scan")
@ResponseBody
public class HomeController {
    
    private static Logger log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private CodeRepository codeRepository;
    
    Gson gson = new Gson();
    
    
    @GetMapping("alipay")
    public String gateway(HttpServletRequest request,
        HttpServletResponse response) {
        return "欢迎你";
    }
    
    
    @GetMapping("/{openid}/{codeId}")
    public void scan(
        @PathVariable String openid,
        @PathVariable Long codeId,
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        final Code code = codeRepository.findByIdAndUserOpenid(codeId, openid);

        String agent = request.getHeader("User-Agent").toLowerCase();
        
        if (agent.indexOf("micromessenger") > 0) {
            
            QrCodeUtil.encode(code.getWx(), code.getName(), response.getOutputStream());
        } else if (agent.indexOf("alipayclient") > 0) {
            
            log.info("阿里支付");
            aliPay(response);
        }else {
            aliPay(response);
        }
    }
    
    
  
    
    
    
    public String aliPay(HttpServletResponse response) throws AlipayApiException, IOException {
        
        AlipayClient alipayClient = new DefaultAlipayClient(
            AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
            AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
      
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 在公共参数中设置回跳和通知地址
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("out_trade_no", "2019040963865222");
        String bizContent = gson.toJson(map);

        alipayRequest.setBizContent(bizContent);// 填充业务参数
        
        String form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        AlipayTradeWapPayResponse aliRresponse = alipayClient.execute(alipayRequest);
        if(aliRresponse.isSuccess()){
        System.out.println("调用成功");
        } else {
        System.out.println("调用失败");
        }
        return form;
    }
    
  
}
