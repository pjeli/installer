package installer.runner;

import installer.exceptions.FatalInstallerException;
import installer.transactions.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseInstaller implements Installer {
  private List<Transaction> list = new ArrayList<Transaction>();
  private boolean success = false;
  private boolean partialState = false;
  private int transactionsProcessed = 0;
  private Exception applyEx;
  private Exception revertEx;

  public void addOperation(Transaction transaction) {
    list.add(transaction);
  }

  public void run() throws Exception {
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
      transactionsProcessed++;
    }

    if(applyEx == null && revertEx == null) {
      success = true;
      return;
    }

    for(int backwards = transactionsProcessed; backwards > 0; backwards--) {
      Transaction toRevert = list.get(backwards);
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
  
  public List<Transaction> getSuccessfulTransactions() {
    return Collections.unmodifiableList(list.subList(0, transactionsProcessed));
  }
  
  public List<Transaction> getFailedTransactions() {
    return Collections.unmodifiableList(list.subList(transactionsProcessed, list.size()));
  }
}
