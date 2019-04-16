package com.itheima.service;

import com.itheima.domain.Perms;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
   public User findByname(String username);

    public List<Perms> findPerms(int userid);
}
