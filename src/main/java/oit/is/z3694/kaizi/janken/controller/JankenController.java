package oit.is.z3694.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList; // ★ 追加

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3694.kaizi.janken.model.Janken;
import oit.is.z3694.kaizi.janken.model.User; // ★ 追加
import oit.is.z3694.kaizi.janken.model.UserMapper; // ★ 追加

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper; // ★ Entry ではなく UserMapper を DI する

  @GetMapping("/janken")
  public String jankenGet(@RequestParam(name = "hand", required = false) String hand, Principal prin, ModelMap model) {

    // ログインユーザー名の取得
    if (prin != null) {
      String loginUser = prin.getName();
      model.addAttribute("login_user", loginUser);
    }

    // じゃんけんの手が渡された場合の処理（既存の処理）
    if (hand != null) {
      Janken janken = new Janken(hand);
      model.addAttribute("janken", janken);
    }

    // ★ DBから全ユーザー（CPU, ほんだ等）を取得して Model に "users" という名前で追加
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);

    return "janken.html";
  }

  // ★ GETリクエストで match.html に遷移するメソッドを追加
  @GetMapping("/match")
  public String match(@RequestParam("id") int id, ModelMap model) {
    // クエリパラメータのidから対戦相手情報を取得
    User opponent = userMapper.selectById(id);
    model.addAttribute("opponent", opponent);

    return "match.html";
  }
}
