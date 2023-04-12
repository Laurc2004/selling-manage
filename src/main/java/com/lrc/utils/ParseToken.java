package com.lrc.utils;

import com.alibaba.fastjson.JSON;
import com.lrc.common.JwtUtil;
import com.lrc.entity.Admin;

public class ParseToken {
    public static Admin parseToken(String token){
        Admin admin = null;
//        解析 token
        try {
            admin = JwtUtil.parseToken(token, Admin.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        JSON对象转为 Admin.class
        admin = JSON.parseObject(JSON.toJSONString(admin),Admin.class);
        return admin;
    }
}
