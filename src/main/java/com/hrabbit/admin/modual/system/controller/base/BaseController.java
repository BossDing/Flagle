package com.hrabbit.admin.modual.system.controller.base;

import com.github.pagehelper.PageInfo;
import com.hrabbit.admin.core.common.file.FileUtil;
import com.hrabbit.admin.core.common.http.HttpUtils;
import com.hrabbit.admin.core.response.BaseResponse;
import com.hrabbit.admin.core.response.TableResultResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("all")
public class BaseController {

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

    protected static BaseResponse SuccessResponse = new BaseResponse();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpUtils.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpUtils.getResponse();
    }

    protected HttpSession getSession() {
        return HttpUtils.getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpUtils.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpUtils.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpUtils.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    /**
     * 删除cookie
     *
     * @author hrabbit
     * @Param: [cookieName]
     * @Date: 2019/1/12 4:26 PM
     */
    protected void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     *
     * @author hrabbit
     * @Param: [page]
     * @Date: 2019/1/12 4:26 PM
     */
    protected <T> TableResultResponse<T> packForBT(PageInfo<T> page) {
        return new TableResultResponse<T>(page);
    }

    /**
     * 返回前台文件流
     *
     * @author hrabbit
     * @Param: [fileName, filePath]
     * @Date: 2019/1/12 4:26 PM
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     * @author hrabbit
     * @Param: [fileName, fileBytes]
     * @Date: 2019/1/12 4:26 PM
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
}
