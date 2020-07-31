package com.hw.springcloud.dao;

import com.hw.springcloud.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao {

    @Select("SELECT * FROM user where username=#{a} and password = #{b}")
    public User selectuser(@Param("a") String username, @Param("b") String password);

    @Select("SELECT count(*) FROM user where username=#{username}  ")
    public int count(String username);

    @Insert(" INSERT INTO user(username,password) VALUE(#{a},#{b})")
    public void inseruser(@Param("a") String username, @Param("b") String password);
}
