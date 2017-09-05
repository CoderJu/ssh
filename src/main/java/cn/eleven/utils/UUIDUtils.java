package cn.eleven.utils;

import java.util.UUID;

/**
 * 生成UUID作为激活码 生成随机字符串
 * Created by User on 2017/9/5.
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
