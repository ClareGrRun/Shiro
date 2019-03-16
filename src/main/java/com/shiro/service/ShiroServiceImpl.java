package com.shiro.service;

import com.shiro.mapper.ShiroMapper;
import com.shiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    ShiroMapper shiroMapper;
    @Override
    public User findUserByName(String username) {
        return shiroMapper.findUserByName(username);
    }

    @Override
    public String findRoleById(Integer id) {
        return shiroMapper.findRoleById(id);
    }
}
