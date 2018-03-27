package installer.transactions;

public class FailOnRevert implements Transaction {
  public void apply() throws Exception {}
  public void revert() throws Exception {
    throw new Exception("FailOnRevert");
  }
}
