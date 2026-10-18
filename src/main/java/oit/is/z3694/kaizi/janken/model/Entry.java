package oit.is.z3694.kaizi.janken.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Entry {
  private ArrayList<String> users = new ArrayList<>();

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

  public void addUser(String name) {
    // 重複登録を防ぐための判定
    for (String s : this.users) {
      if (s.equals(name)) {
        return;
      }
    }
    this.users.add(name);
  }
}
