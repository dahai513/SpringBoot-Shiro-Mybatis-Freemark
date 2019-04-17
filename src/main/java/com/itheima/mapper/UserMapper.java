package com.itheima.mapper;

import com.itheima.domain.Perms;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select( "select id,username,password,password_salt from users where username=#{username} " )
    public User findUsername(String username);

    @Select( "select id,userid,perm from perms where userid=#{userid} " )
    public List<Perms> findPerms(int userid);
}
