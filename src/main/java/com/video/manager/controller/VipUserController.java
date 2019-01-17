package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TUser;
import com.video.manager.service.VipUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Controller
@RequestMapping("admin/vipUser")
public class VipUserController {
    @Autowired
    VipUserService vipUserService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TUser user, ModelMap map){
        BasePage.beanToMap(user,map);
        PageInfo<TUser> orderPageInfo = vipUserService.getVipUser(user,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/vipUser/index";
    }
}