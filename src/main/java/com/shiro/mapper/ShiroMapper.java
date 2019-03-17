package com.shiro.mapper;

import com.shiro.pojo.Menu;
import com.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ShiroMapper {
   User findUserByName(@Param("username") String username);

   String findRoleById(Integer id);

   List<Menu> patterns();

   List<String> roles(Integer id);
}
