package oit.is.z3694.kaizi.janken.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3694.kaizi.janken.model.Entry;
import oit.is.z3694.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @Autowired
  private Entry entry; // ★ Entryオブジェクトの注入 (DI)

  @GetMapping("/janken")
  public String jankenGet(@RequestParam(name = "hand", required = false) String hand, Principal prin, ModelMap model) {

    // ★ ログインユーザー名をEntryに追加
    if (prin != null) {
      String loginUser = prin.getName();
      this.entry.addUser(loginUser);
      model.addAttribute("login_user", loginUser);
    }

    // じゃんけんの手が渡された場合の処理
    if (hand != null) {
      Janken janken = new Janken(hand);
      model.addAttribute("janken", janken);
    }

    // ★ EntryオブジェクトをModelに登録して画面へ渡す
    model.addAttribute("entry", this.entry);

    return "janken.html";
  }
}
