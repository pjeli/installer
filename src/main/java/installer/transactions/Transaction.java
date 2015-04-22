package installer.transactions;

public interface Transaction {
    public void apply() throws Exception;
    public void revert() throws Exception;
}
