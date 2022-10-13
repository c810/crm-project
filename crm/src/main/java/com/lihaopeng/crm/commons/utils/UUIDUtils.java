package com.lihaopeng.crm.commons.utils;

import java.util.UUID;

/**
 * @author Connor
 * @date 2022/10/13 16:44
 */
public class UUIDUtils {
    /**
     * 获取UUID的值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
