package com.shiro.service;

import com.shiro.pojo.User;
import org.springframework.stereotype.Service;

public interface ShiroService {
    public User findUserByName(String username);

    public String findRoleById(Integer id);
}
