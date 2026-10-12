package oit.is.z3694.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  /**
   * AC2: GETアクセス時 (/janken に直接アクセス)
   * Model に username を追加しないため、「Hi taro」は表示されない
   */
  @GetMapping("/janken")
  public String jankenGet() {
    return "janken.html";
  }

  /**
   * AC1: POSTアクセス時 (index.html からフォーム送信)
   * Model に username を登録して渡すため、「Hi taro」が表示される
   */
  @PostMapping("/janken")
  public String jankenPost(@RequestParam("username") String username, ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }
}
