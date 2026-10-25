package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, name FROM users;")
  ArrayList<User> selectAllUsers();

  // ★ idを利用して特定のUserオブジェクトを取得するメソッドを追加
  @Select("SELECT id, name FROM users WHERE id = #{id};")
  User selectById(int id);

  // ★ nameからUserオブジェクトを取得するメソッドを追加
  @Select("SELECT id, name FROM users WHERE name = #{name};")
  User selectByName(String name);

}
