package installer.transactions;

/**
 * Transaction objects are the level at which the Installer works on.
 * The goal of the transaction is that it should either completely succeed
 * or completely fail.
 *
 * If the Installer ever aborts Transactions should also allow to be reverted
 * to their original state.
 *
 * The end result of doing an apply() and a revert() should be that it
 * appears as if nothing happened.
 */
public interface Transaction {
    void apply() throws Exception;
    void revert() throws Exception;
}
