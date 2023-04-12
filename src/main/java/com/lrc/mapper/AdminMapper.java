package com.lrc.mapper;

import com.lrc.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin selectOne(Integer id);

    Admin login(String adminName);

    void update(Admin admin);

    void save(Admin admin);

    List<Admin> select();
}
