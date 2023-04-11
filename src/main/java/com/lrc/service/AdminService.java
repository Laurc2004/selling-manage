package com.lrc.service;

import com.lrc.common.Result;
import com.lrc.entity.Admin;

import java.util.Map;

public interface AdminService {
//    Admin selectOne(Integer id);

    Result<Map<String,Object>> login(Admin admin);

    Result<Map<String,Object>> info(String token);

//    Result<?> update(Admin admin);
}
