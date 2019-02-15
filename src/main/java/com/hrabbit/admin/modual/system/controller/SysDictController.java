package com.hrabbit.admin.modual.system.controller;

import com.hrabbit.admin.modual.system.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统字典控制器
 *
 * @Auther: hrabbit
 * @Date: 2019-02-12 9:39 AM
 * @Description:
 */
@Controller
@RequestMapping("dict")
public class SysDictController extends BaseController {

    private static final String BASEURL = "/system/dict/";

    /**
     * 进入到主页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return BASEURL + "/dict_info.html";
    }

}
