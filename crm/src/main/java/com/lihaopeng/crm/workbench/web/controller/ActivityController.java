package com.lihaopeng.crm.workbench.web.controller;

import com.lihaopeng.crm.commons.constants.Constants;
import com.lihaopeng.crm.commons.domain.ReturnObject;
import com.lihaopeng.crm.commons.utils.DateUtils;
import com.lihaopeng.crm.commons.utils.UUIDUtils;
import com.lihaopeng.crm.settings.domain.User;
import com.lihaopeng.crm.settings.service.UserService;
import com.lihaopeng.crm.workbench.domain.Activity;
import com.lihaopeng.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Connor
 * @date 2022/10/13 14:11
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request) {
        // 调用service层方法,查询所有的用户
        List<User> userList = userService.queryAllUsers();
        // 把数据保存在request中
        request.setAttribute("userList", userList);
        // 请求转发到市场活动的主页面
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        // 封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDataTime(new Date()));
        activity.setCreateBy(user.getId()); // 不用用户名字和账号,用id,相当于user表和activity表有一对多的关系

        ReturnObject returnObject = new ReturnObject();
        // 查询数据不用写try..catch,但是修改保存等需要看报不报异常
        try {
            // 调用service层方法,保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试...");
        }

        return returnObject;
    }
}
