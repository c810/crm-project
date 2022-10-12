package com.lihaopeng.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Connor
 * @date 2022/10/11 15:10
 */
//在springmvc中配置包扫描,扫描此类所在包com.lihaopeng.crm.web.controller
@Controller
public class IndexController {
    //public: springmvc核心控制器来调用此方法,核心控制器类和当前类所在包不同,且没有继承关系,所以要想调用,必须设置为public属性
    //将来一访问http://127.0.0.1:8080/crm/就来执行此方法,理论上给Controller方法分配url要写http://127.0.0.1:8080/crm/
    //但为了简便,http://127.0.0.1:8080/crm必须省去,剩下的/就代表应用的根(用/代表应用根目录下的/)
    //@RequestMapping("http://127.0.0.1:8080/crm/")
    @RequestMapping("/")
    public String index () {
        //请求转发
        //用mvc中的视图解析器省略/WEB-INF/pages/和.jsp
        //return "/WEB-INF/pages/index.jsp";
        return "index";
    }
}
