package installer.runner;

import installer.exceptions.FatalInstallerException;
import installer.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BaseInstaller implements Installer {
  private List<Transaction> list = new ArrayList<Transaction>();
  private boolean success = false;
  private boolean partialState = false;
  private Exception applyEx;
  private Exception revertEx;

  public void addOperation(Transaction transaction) {
    list.add(transaction);
  }

  public void run() throws Exception {
    int txId = 0;
    for(Transaction toApply : list) {
      try {
        toApply.apply();
      } catch (Exception applyEx) {
        this.applyEx = applyEx;
        try {
          toApply.revert();
        } catch (Exception revertEx) {
          this.revertEx = revertEx;
          partialState = true;
          throw new FatalInstallerException(applyEx, revertEx);
        }
        break;
      }
      txId++;
    }

    if(applyEx == null && revertEx == null) {
      success = true;
      return;
    }

    for(; txId > 0; txId--) {
      Transaction toRevert = list.get(txId);
      try {
        toRevert.revert();
      } catch (Exception revertEx) {
        this.revertEx = revertEx;
        partialState = true;
        throw new FatalInstallerException(applyEx, revertEx);
      }
    }

    partialState = false;
    success = false;
  }

  public boolean wasSuccessful() {
    return success;
  }

  public boolean wasFailure() {
    return !success;
  }

  public boolean finishedWithPartialState() {
    return partialState;
  }

  public Exception getApplyEx() {
    return applyEx;
  }

  public Exception getRevertEx() {
    return revertEx;
  }
}
