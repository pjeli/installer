package installer.transactions;

public class FailOnApply implements Transaction {
  public void apply() throws Exception {
    throw new Exception("FailOnApply");
  }
  public void revert() throws Exception {}
  public String toString() {
    return "FailOnApply{}";
  }
}
