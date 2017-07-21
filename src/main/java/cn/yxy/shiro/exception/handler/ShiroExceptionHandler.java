package cn.yxy.shiro.exception.handler;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2017/7/21.
 */
@ControllerAdvice
public class ShiroExceptionHandler {

    /**
     * 处理没有权限 异常
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(Model model, UnauthorizedException e) {
        model.addAttribute("code", -1003);
        return "common/fail";
    }
}
