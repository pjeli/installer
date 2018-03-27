package installer.transactions;

public class FailOnApplyAndRevert implements Transaction {
  public void apply() throws Exception {
    throw new Exception("FailOnApplyAndRevert");
  }

  public void revert() throws Exception {
    throw new Exception("FailOnApplyAndRevert");
  }
}
