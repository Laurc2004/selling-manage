package com.lrc.mapper;

import com.lrc.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin selectOne(Integer id);

    Admin login(String adminName);

    void save(Admin admin);
}
