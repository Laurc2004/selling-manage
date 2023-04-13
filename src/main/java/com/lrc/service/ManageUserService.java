package com.lrc.service;

import com.lrc.common.Result;
import com.lrc.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface ManageUserService {
    Result<?> save(User user, HttpServletRequest request);

    Result<?> selectAll();

    Result<?> getUserById(Integer id);

    Result<?> update(User user,HttpServletRequest request);

    Result<?> deleteUserById(Integer id);
}
