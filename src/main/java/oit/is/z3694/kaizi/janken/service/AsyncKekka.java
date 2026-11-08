package oit.is.z3694.kaizi.janken.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3694.kaizi.janken.model.Match;
import oit.is.z3694.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  private MatchMapper matchMapper;

  @Async
  public void asyncShowMatchResult(SseEmitter emitter, int myUserId) {
    try {
      while (true) {
        // 0.5秒ごとに自分が関わる isActive = TRUE の試合があるかDBをチェック
        TimeUnit.MILLISECONDS.sleep(500);

        Match activeMatch = matchMapper.selectActiveMatchByUserId(myUserId);

        if (activeMatch != null) {
          // 試合結果を画面へ送信
          emitter.send(activeMatch);

          // 後処理: matches の isActive を FALSE に更新
          matchMapper.updateActiveToFalse(activeMatch.getId());
          break;
        }
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
  }
}
