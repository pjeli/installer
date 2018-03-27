package installer;

import static org.junit.Assert.fail;

import installer.runner.BaseInstaller;
import installer.transactions.AlwaysSucceed;
import installer.transactions.FailOnApply;
import installer.transactions.FailOnApplyAndRevert;
import installer.transactions.FailOnRevert;
import installer.transactions.Transaction;
import org.junit.Test;

public class TestInstaller {

  @Test
  public void testInstaller() {
    BaseInstaller fakeInstaller = new BaseInstaller();
    
    fakeInstaller.addOperation(new AlwaysSucceed());
    fakeInstaller.addOperation(new AlwaysSucceed());

    try {
      fakeInstaller.run();
    } catch (Exception e) {
      fail();
    }
    
    assert fakeInstaller.wasSuccessful();

    printTransactions(fakeInstaller);
  }

  @Test
  public void testInstallerFailOnApply() {
    BaseInstaller fakeInstaller = new BaseInstaller();

    fakeInstaller.addOperation(new AlwaysSucceed());
    fakeInstaller.addOperation(new FailOnApply());

    try {
      fakeInstaller.run();
    } catch (Exception e) {
      fail();
    }

    assert !fakeInstaller.wasSuccessful();
    assert !fakeInstaller.finishedWithPartialState();
    assert fakeInstaller.getApplyEx() != null;
    assert fakeInstaller.getRevertEx() == null;

    printTransactions(fakeInstaller);
  }

  @Test
  public void testInstallerFailOnRevertOnly() {
    BaseInstaller fakeInstaller = new BaseInstaller();

    fakeInstaller.addOperation(new AlwaysSucceed());
    fakeInstaller.addOperation(new FailOnRevert());

    try {
      fakeInstaller.run();
    } catch (Exception e) {
      fail();
    }

    assert fakeInstaller.wasSuccessful();

    printTransactions(fakeInstaller);
  }

  @Test
  public void testInstallerFailOnApplyAndRevertSeparately() {
    BaseInstaller fakeInstaller = new BaseInstaller();

    fakeInstaller.addOperation(new AlwaysSucceed());
    fakeInstaller.addOperation(new FailOnRevert());
    fakeInstaller.addOperation(new FailOnApply());

    try {
      fakeInstaller.run();
    } catch (Exception ignored) {}

    assert !fakeInstaller.wasSuccessful();
    assert fakeInstaller.finishedWithPartialState();
    assert fakeInstaller.getApplyEx() != null;
    assert fakeInstaller.getRevertEx() != null;
    assert fakeInstaller.getSuccessfulTransactions().size() == 2;
    assert fakeInstaller.getFailedTransactions().size() == 1;

    printTransactions(fakeInstaller);
  }

  @Test
  public void testInstallerFailOnApplyAndRevertTogether() {
    BaseInstaller fakeInstaller = new BaseInstaller();

    fakeInstaller.addOperation(new AlwaysSucceed());
    fakeInstaller.addOperation(new FailOnApplyAndRevert());

    try {
      fakeInstaller.run();
    } catch (Exception ignored) {}

    assert !fakeInstaller.wasSuccessful();
    assert fakeInstaller.finishedWithPartialState();
    assert fakeInstaller.getApplyEx() != null;
    assert fakeInstaller.getRevertEx() != null;
    assert fakeInstaller.getSuccessfulTransactions().size() == 1;
    assert fakeInstaller.getFailedTransactions().size() == 1;

    printTransactions(fakeInstaller);
  }

  @Test
  public void testInstallerFailImmediately() {
    BaseInstaller fakeInstaller = new BaseInstaller();

    fakeInstaller.addOperation(new FailOnApplyAndRevert());

    try {
      fakeInstaller.run();
    } catch (Exception ignored) {}

    assert !fakeInstaller.wasSuccessful();
    assert fakeInstaller.finishedWithPartialState();
    assert fakeInstaller.getApplyEx() != null;
    assert fakeInstaller.getRevertEx() != null;
    assert fakeInstaller.getSuccessfulTransactions().size() == 0;
    assert fakeInstaller.getFailedTransactions().size() == 1;

    printTransactions(fakeInstaller);
  }

  private void printTransactions(BaseInstaller fakeInstaller) {
    System.out.println("SUCCEEDED: " + fakeInstaller.getSuccessfulTransactions().size());
    for(Transaction tx : fakeInstaller.getSuccessfulTransactions()) {
      System.out.println(tx.toString());
    }
    System.out.println("FAILED: " + fakeInstaller.getFailedTransactions().size());
    for(Transaction tx : fakeInstaller.getFailedTransactions()) {
      System.out.println(tx.toString());
    }
  }
}
