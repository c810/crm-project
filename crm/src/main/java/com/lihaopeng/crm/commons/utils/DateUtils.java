package com.lihaopeng.crm.commons.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author Connor
 * @date 2022/10/12 8:49
 */
/**
 * 对Date类型数据进行处理的工具类
 */
public class DateUtils {
    // 工具类定义静态方法,调用时不用new对象,直接通过类名调用

    /**
     * 对指定的date对象进行格式化: yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDataTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 对指定的date对象进行格式化: yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatData(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 对指定的date对象进行格式化: HH:mm:ss
     * @param date
     * @return
     */
    public static String formatTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
