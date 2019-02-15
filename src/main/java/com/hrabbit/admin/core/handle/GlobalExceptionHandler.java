package com.hrabbit.admin.core.handle;

import com.hrabbit.admin.core.common.http.HttpUtils;
import com.hrabbit.admin.core.log.LogManager;
import com.hrabbit.admin.core.log.factory.LogTaskFactory;
import com.hrabbit.admin.core.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常类
 *
 * @Auther: hrabbit
 * @Date: 2018-11-15 3:40 PM
 * @Description:
 */
@ControllerAdvice("com.hrabbit.admin")
@Order(-1)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 其他异常抛出信息
     *
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        log.error(ex.getMessage(), ex);
        return new BaseResponse(500, ex.getMessage());
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login.html";
    }

    /**
     * 账号密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String username = HttpUtils.getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号密码错误", HttpUtils.getIp()));
        model.addAttribute("message", "账号密码错误");
        return "/login.html";
    }


}