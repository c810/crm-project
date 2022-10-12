package com.lihaopeng.crm.commons.constants;

/**
 * @author Connor
 * @date 2022/10/12 9:07
 */

/**
 * 保存以后需要用到的所有常量,方便后期维护修改
 * public 因为是常量,就是让别的类引用,能用的范围大越好
 * static 整个项目中只有一份
 * final 不能修改了
 * 常量名全部大写,下划线分割
 */
public class Constants {
    // 保存ReturnObject类中的Code值
    public static final String RETURN_OBJECT_CODE_SUCCESS = "1"; // 成功
    public static final String RETURN_OBJECT_CODE_FAIL = "0"; // 失败
    // 保存当前用户的key
    public static final String SESSION_USER = "sessionUser";
}
