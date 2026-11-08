package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchInfoMapper {

  // アクティブな試合（isActive = true）を取得する
  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchinfo WHERE isActive = TRUE;")
  ArrayList<MatchInfo> selectActiveMatchInfo();

  // 新しい試合待ち情報をINSERTする
  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);

}
