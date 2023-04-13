package com.lrc.controller;

import com.lrc.common.Result;
import com.lrc.entity.User;
import com.lrc.service.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manageUser")
public class ManageUserController {
    @Autowired
    private ManageUserService manageUserService;
    @PostMapping("/save")
    public Result<?> save(@RequestBody User user, HttpServletRequest request){
        return manageUserService.save(user,request);
    }

    @GetMapping("/selectAll")
    public Result<?> selectAll(){
        return manageUserService.selectAll();
    }

    @GetMapping("/{id}")
    public Result<?> getUserById(@PathVariable Integer id){
        return manageUserService.getUserById(id);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody User user, HttpServletRequest request){
        return manageUserService.update(user,request);
    }

    @PostMapping("/delete/{id}")
    public Result<?> deleteUserById (@PathVariable Integer id){
        return manageUserService.deleteUserById(id);
    }
}
