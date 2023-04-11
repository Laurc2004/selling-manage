package com.lrc.controller;

import com.lrc.common.Result;
import com.lrc.entity.Admin;
import com.lrc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登录接口
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody Admin admin){
        return adminService.login(admin);
    }

    /**
     * info信息
     * @param token
     * @return
     */

    @GetMapping("/info")
    public Result<Map<String,Object>> info(String token){
        return adminService.info(token);
    }

    /**
     * 登出接口 不写逻辑 前端去除 token
     * @return
     */

    @GetMapping ("/logout")
    public Result<?> logout(){
        return Result.success("退出成功");
    }

    /**
     * 修改管理员接口
     * @param admin
     * @return
     */

//    @PostMapping("/update")
//    public Result<?> update(@RequestBody Admin admin){
//        return adminService.update(admin);
//    }

    /**
     * 测试查找用户
     * @param id
     * @return
     */

//    @GetMapping("selectOne/{id}")
//    public Admin selectOne(@PathVariable Integer id){
//        return adminService.selectOne(id);
//    }
}
