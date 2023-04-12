package com.lrc.controller;

import com.lrc.common.Result;
import com.lrc.entity.Admin;
import com.lrc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin
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
     * @return R
     */
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody Admin admin){
        return adminService.login(admin);
    }

    /**
     * info信息
     * @param token
     * @return R
     */

    @GetMapping("/info")
    public Result<Map<String,Object>> info(@RequestParam String token){
        return adminService.info(token);
    }

    /**
     * 登出接口 不写逻辑 前端去除 token
     * @return R
     */

    @GetMapping ("/logout")
    public Result<?> logout(@RequestHeader("token") String token){
        return adminService.logout(token);
    }

    /**
     * 修改管理员接口 测试用
     * @param admin
     * @return R
     */

    @PostMapping("/update")
    public Result<?> update(@RequestBody Admin admin){
        return adminService.update(admin);
    }

    /**
     * 新增管理员接口 测试用
     * @param admin
     * @return R
     */

    @PostMapping("/save")
    public Result<?> save(@RequestBody Admin admin){
        return adminService.save(admin);
    }

    /**
     * 查询所有管理员 测试用
     * @return R
     */
    @GetMapping("/select")
    public Result<?> select(){
        return adminService.select();
    }

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
