package com.hrabbit.admin.modual.system.controller;

import com.hrabbit.admin.modual.system.bean.SysMenu;
import com.hrabbit.admin.modual.system.controller.base.BaseController;
import com.hrabbit.admin.modual.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源控制层
 *
 * @Auther: hrabbit
 * @Date: 2018-12-30 3:39 PM
 * @Description:
 */
@Controller
@RequestMapping(value = "menu")
public class SysMenuController extends BaseController {

    private static final String BASEURL = "/system/menu";

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 进入到按钮维护主页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(ModelMap model) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("n");
        model.addAttribute("aa", list);
        return BASEURL + "/menu_info.html";
    }

    /**
     * 根据父级id获取按钮
     *
     * @return
     */
    @RequestMapping(value = "getMenuByParentId/{parentId}",method = RequestMethod.GET)
    @ResponseBody
    public Object getTopMenu(@PathVariable("parentId") Integer parentId) {
        List<SysMenu> menuByParentId = sysMenuService.findMenuByParentId(parentId);
        return menuByParentId;
    }

}
