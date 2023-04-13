package com.lrc.mapper;

import com.lrc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageUserMapper {
    Integer save(User user);

    List<User> selectAll();

    User getUserById(Integer id);

    Integer update(User user);

    Integer deleteUserById(Integer id);
}
