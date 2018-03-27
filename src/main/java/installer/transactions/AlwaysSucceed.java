package installer.transactions;

public class AlwaysSucceed implements Transaction {
  public void apply() throws Exception {}
  public void revert() throws Exception {}
  public String toString() {
    return "AlwaysSucceed{}";
  }
}
