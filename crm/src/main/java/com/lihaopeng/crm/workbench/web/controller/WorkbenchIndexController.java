package com.lihaopeng.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Connor
 * @date 2022/10/12 8:32
 */
@Controller // 只要加注解就要想到扫描包
public class WorkbenchIndexController {
    // TODO: 登录成功后跳转过来,请求转发到业务主页面
    @RequestMapping("/workbench/index.do") // 要和访问的资源路径保持一致
    public String index(){
        // 跳转到业务主页面
        return "workbench/index";
    }
}
