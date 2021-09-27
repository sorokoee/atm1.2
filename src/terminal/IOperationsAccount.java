package terminal;

public interface IOperationsAccount {
    int getAccountBalance();
    void topUpBalance(int denomination);
    void getCash(int summa);
}
