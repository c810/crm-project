package com.lihaopeng.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Connor
 * @date 2022/10/13 12:52
 */
@Controller
public class MainController {
    @RequestMapping("/workbench/main/index.do")
    public String index(){
        // 跳转到main/index.jsp
        return "workbench/main/index";
    }
}
