package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT id, user1, user2, user1Hand, user2Hand, isActive FROM matches;")
  ArrayList<Match> selectAllMatches();

  // ★ 試合結果をmatchesテーブルに登録するメソッドを追加
  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatch(Match match);

  // ★ task4追加: isActive = TRUE の最新のMatchを取得
  @Select("SELECT id, user1, user2, user1Hand, user2Hand, isActive FROM matches WHERE isActive = TRUE ORDER BY id DESC LIMIT 1;")
  Match selectActiveMatch();

  // ★ task4追加: matches の isActive を FALSE に更新
  @Update("UPDATE matches SET isActive = FALSE WHERE id = #{id};")
  void updateActiveToFalse(int id);

  // ★ 自分が関わっている isActive = TRUE の最新試合を取得
  @Select("SELECT id, user1, user2, user1Hand, user2Hand, isActive FROM matches WHERE (user1 = #{userId} OR user2 = #{userId}) AND isActive = TRUE ORDER BY id DESC LIMIT 1;")
  Match selectActiveMatchByUserId(int userId);

}
