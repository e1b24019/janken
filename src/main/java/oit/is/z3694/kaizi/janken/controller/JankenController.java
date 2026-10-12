package oit.is.z3694.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3694.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String jankenGet(@RequestParam(name = "hand", required = false) String hand, ModelMap model) {
    // ★追加：クエリパラメータ ?hand=... が渡された場合の処理
    if (hand != null) {
      Janken janken = new Janken(hand);
      model.addAttribute("janken", janken);
    }
    return "janken.html";
  }

  @PostMapping("/janken")
  public String jankenPost(@RequestParam("username") String username, ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }
}
