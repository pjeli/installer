package installer.runner;

import installer.transactions.Transaction;

public interface Installer {
    /**
     * Appends a transaction to the installer. This added transaction will
     * be performed after all the other added ones. FIFO order.
     * @param transaction the transaction to perform next
     */
    void addOperation(Transaction transaction);

    void run() throws Exception;

    boolean wasSuccessful();

    boolean wasFailure();

    boolean finishedWithPartialState();
}
