package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchMapper {

  @Select("SELECT id, user1, user2, user1Hand, user2Hand FROM matches;")
  ArrayList<Match> selectAllMatches();

  // ★ 試合結果をmatchesテーブルに登録するメソッドを追加
  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatch(Match match);

}
