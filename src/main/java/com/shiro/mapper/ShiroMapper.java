package com.shiro.mapper;

import com.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ShiroMapper {
   User findUserByName(@Param("username") String username);

   String findRoleById(Integer id);
}
