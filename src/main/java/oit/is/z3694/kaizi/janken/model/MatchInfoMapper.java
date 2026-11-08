package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  // アクティブな試合（isActive = true）を取得する
  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchinfo WHERE isActive = TRUE;")
  ArrayList<MatchInfo> selectActiveMatchInfo();

  // ★ task3追加: 特定の対戦カード(user1=相手, user2=自分)で待機中のMatchInfoを取得
  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchinfo WHERE user1 = #{user1} AND user2 = #{user2} AND isActive = TRUE;")
  MatchInfo selectActiveMatchInfoByUser1AndUser2(int user1, int user2);

  // 新しい試合待ち情報をINSERTする
  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);

  // ★ task3追加: 試合成立時に matchinfo の isActive を FALSE に更新
  @Update("UPDATE matchinfo SET isActive = FALSE WHERE id = #{id};")
  void updateActiveToFalse(int id);

}
