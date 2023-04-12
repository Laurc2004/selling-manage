package com.lrc.service.impl;

import com.alibaba.fastjson.JSON;
import com.lrc.common.JwtUtil;
import com.lrc.common.Result;
import com.lrc.entity.Admin;
import com.lrc.mapper.AdminMapper;
import com.lrc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Result<Map<String,Object>> login(Admin admin) {
        Admin adm = adminMapper.login(admin.getAdminName());
//        空值校验
        if (adm == null){
            return Result.failure(404,"该用户不存在或账号错误");
        }
//        密码校验
        if (!passwordEncoder.matches(admin.getPassword(),adm.getPassword())){
            return Result.failure(405,"密码错误");
        }

//        生成 token
        String token = JwtUtil.createToken(adm);
//        创建 Map 并存入 token
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.success("登录成功",map);
    }

    @Override
    public Result<Map<String,Object>> info(String token) {
        Admin admin = null;
//        解析 token
        try {
            admin = JwtUtil.parseToken(token, Admin.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        空值校验
        if (admin == null){
            return Result.failure(403,"token失效");
        }
//        JSON对象转为 Admin.class
        admin = JSON.parseObject(JSON.toJSONString(admin),Admin.class);
//        创建 HashMap 取出 name
        HashMap<String,Object> map = new HashMap<>();
        map.put("adminId",admin.getId());
        map.put("adminName",admin.getAdminName());
        return Result.success(map);
    }

    @Override
    public Result<?> logout(String token) {
        return Result.success("退出登录成功",null);
    }

    @Override
    public Result<?> update(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.update(admin);
        return Result.success(admin);
    }

    @Override
    public Result<?> save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.save(admin);
        return Result.success(admin);
    }

    @Override
    public Result<?> select() {
        List<Admin> admin;
        admin = adminMapper.select();
        if (admin == null){
            return Result.failure(404,"获取数据失败");
        }
        return Result.success(admin);
    }
//
//    @Override
//    public Admin selectOne(Integer id) {
//        return adminMapper.selectOne(id);
//    }

}
