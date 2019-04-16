package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select( "select id,username,password from users where username=#{username} " )
    public User finUsername(String username);
}
