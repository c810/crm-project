package com.lihaopeng.crm.settings.web.controller;

import com.lihaopeng.crm.commons.constants.Constants;
import com.lihaopeng.crm.commons.domain.ReturnObject;
import com.lihaopeng.crm.commons.utils.DateUtils;
import com.lihaopeng.crm.settings.domain.User;
import com.lihaopeng.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Connor
 * @date 2022/10/11 16:29
 */
//别忘了在mvc添加包扫描
@Controller
public class UserController {

    //一定要注入service层的对象
    @Autowired
    private UserService userService;

    //这个url要和controller方法处理完请求之后,响应信息返回的页面的资源目录保持一致(这里是src/main/webapp/WEB-INF/pages/settings/qx/user/login.jsp)
    //最后的资源名称要和方法名保持一致,同时一般习惯加一个.do,配置核心控制器的时候把.do资源也交给它处理
    //@RequestMapping("/WEB-INF/pages/settings/qx/user/toLogin.do")
    //又发现,将来所有的controller不论返回到那个页面,页面都在/WEB-INF/pages下,所以也可以省略不写
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        //请求转发到登录页面
        //记住,视图解析器里已经配了前缀后缀,所以这里settings前面不需要加/了
        return "settings/qx/user/login";
    }

    //将来我们要通过这个返回值返回响应一个json对象{code:1|0,message:xxx},就要把这个返回值封装成一个java对象(才能调用返回json对象)
    //那么返回什么类型的对象呢?如果返回String的话得自己转成json对象,肯定不行;如果用User类的话,User类里面也没有code和message对象,也不行
    //所以我们要自己创建一个java类,专门封装这种要返回json数据的对象;再想,我们现在只需要返回code和message这两条数据,如果以后要返回的增加了呢?
    //所以我们要返回一个更加通用的Object,将来不管返回什么类型的java对象都可以,想返回什么样的json都可以,这就是多态
    //要返回一个json对象,就要加一个@ResponseBody,写在外面也是一样的
    //@ResponseBody
    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request) {
        //封装参数
        //封装的key起的名要和(UserMapper.xml中)数据库sql语句用的#{loginPwd}保持一致
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用service层方法,查询用户
        User user = userService.queryUserByLoginActAndPwd(map);

        //根据查询结果,生成响应信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null) {
            //登录失败,用户名或者密码错误
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或者密码错误");
        } else {
            //可以把user.getExpireTime()这个字符串转成Date,可以自己试一试
            //这里采取把Date转成字符串
            if (DateUtils.formatDataTime(new Date()).compareTo(user.getExpireTime()) > 0) {
                //登录失败,账号已过期
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            } else if ("0".equals(user.getLockState())){
                //登录失败,状态被锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) { //获取远程的用户的ip
                //登录失败,ip受限
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }
        //因为前面已经加了@ResponseBody,会自动帮我们转换成json
        return returnObject;
    }
}
