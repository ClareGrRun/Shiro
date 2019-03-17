package com.shiro.service;

import com.shiro.pojo.Menu;
import com.shiro.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShiroService {
    public User findUserByName(String username);

    public String findRoleById(Integer id);

    List<Menu> patterns();

    List<String> roles(Integer id);
}
