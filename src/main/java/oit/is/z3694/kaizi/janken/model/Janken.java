package oit.is.z3694.kaizi.janken.model;

public class Janken {
  private String myHand;
  private String cpuHand = "Gu"; // CPUの手は固定で「Gu」
  private String result;

  public Janken(String myHand) {
    this.myHand = myHand;
    this.result = judge(myHand, this.cpuHand);
  }

  // 勝敗判定ロジック (CPUの手が Gu の場合)
  private String judge(String myHand, String cpuHand) {
    if (myHand.equals(cpuHand)) {
      return "Draw";
    }
    if (myHand.equals("Pa")) {
      return "You Win!";
    }
    return "You Lose!"; // Choki などの場合
  }

  public String getMyHand() {
    return myHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
