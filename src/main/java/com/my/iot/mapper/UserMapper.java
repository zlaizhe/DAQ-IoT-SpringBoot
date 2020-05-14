package com.my.iot.mapper;

import com.my.iot.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where username = #{value}")
    public User findByUsername(String username);

    @Insert("insert into tb_user values(null, #{username}, #{nickname}, #{password}, #{tel}, #{email})")
    public void insert(User user);

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    public User findByUsernameAndPassword(User user);

    @Update("update tb_user set nickname = #{nickname}, password = #{password}, tel = #{tel}, email = #{email} where id = #{id}")
    void update(User user);
}
