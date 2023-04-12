package com.lrc.service.impl;

import com.lrc.common.Result;
import com.lrc.entity.Admin;
import com.lrc.entity.User;
import com.lrc.mapper.ManageUserMapper;
import com.lrc.service.ManageUserService;
import com.lrc.utils.ParseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManageUserServiceImpl implements ManageUserService {
    @Autowired
    private ManageUserMapper manageUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Result<?> save(User user, HttpServletRequest request) {
        String token = request.getHeader("token");
        Admin admin = null;
        admin = ParseToken.parseToken(token);
//        空值校验
        if (admin == null){
            return Result.failure(403,"token失效");
        }

//        设置adminId 和 逻辑删除 以及时间
        user.setAdminId(admin.getId());
        user.setDeleted(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
//               密码加密
        user.setPassword(passwordEncoder.encode(admin.getPassword()));
//        数据库新增，持久化
        Integer save = manageUserMapper.save(user);
//        判断返回条数
        if (save == 0){
            return Result.failure(500,"新增用户失败");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("phone",user.getPhone());
        map.put("createTime",new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateTime()));
        map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd").format(user.getUpdateTime()));
        map.put("adminId",user.getAdminId());
        return Result.success(map);
    }

    @Override
    public Result<?> selectAll() {
       List<User> users = manageUserMapper.selectAll();
        List<Map<String,Object>> ListMap = new ArrayList<>();
//        选自己需要的属性
        for (User user : users){
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            map.put("phone",user.getPhone());
            map.put("createTime",new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateTime()));
            map.put("updateTime",new SimpleDateFormat("yyyy-MM-dd").format(user.getUpdateTime()));
            map.put("adminId",user.getAdminId());
            map.put("adminName",user.getAdmin().getAdminName());
            ListMap.add(map);
        }

        return Result.success(ListMap);
    }
}
