 package cn.sf.qrcode.home;

import org.apache.shiro.authc.AccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.sf.qrcode.common.domain.HttpState;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/17
 */
@RestControllerAdvice
public class ExceptionController {
    

  
    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public HttpState<String> handleShiroException(Exception ex) {
        return HttpState.error(ex.getMessage());
    }
}

