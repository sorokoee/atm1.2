package terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Account implements IOperationsAccount {
    public static List<NoteAccount> accountList = new ArrayList<NoteAccount>(){
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (NoteAccount noteAccount :accountList) {
                sb.append(
                        String.format("%s=%s\n",
                                noteAccount.getNoteDenomination(),
                                noteAccount.getList().size())
                );
            }
            return sb.toString();
        }
    };

    public Account() {
        accountList.add(new NoteAccount(20));
        accountList.add(new NoteAccount(10));
        accountList.add(new NoteAccount(5));
    }

    public List<NoteAccount> getAccountList() {
        return accountList;
    }

    @Override
    public int getAccountBalance() {
        int sum = 0;
        for (NoteAccount acc : getAccountList()) {
            sum += acc.getList().size() * acc.getNoteDenomination();
        }
        return sum;
    }

    public void getAvailableDenomination(){
        System.out.println("Доступные номиналы:");
        for (NoteAccount n:getAccountList()) {
            if (!n.getList().isEmpty()) {
                System.out.println(n.getNoteDenomination());
            }
        }
    }

    public int getMinAvailableDenomination() {
        List<Integer> list = new ArrayList<>();
        for (NoteAccount n : accountList) {
            if (!n.getList().isEmpty()){
                list.add(n.getNoteDenomination());
            }
        }
        int minDenomination=list.get(0);
        for (Integer i:list) {
            if (i<minDenomination){
                minDenomination=i;
            }
        }
        return minDenomination;
    }

    public int getMinAccountDenomination(){
        int minDenom=accountList.get(0).getNoteDenomination();
        for (NoteAccount n:accountList) {
            if (n.getNoteDenomination()<minDenom){
                minDenom=n.getNoteDenomination();
            }
        }
        return minDenom;
    }

    @Override
    public void topUpBalance(int denomination) {
        Note note = new Note(denomination);
        for (NoteAccount list : getAccountList()) {
            if (note.getDenomination() == list.getNoteDenomination()) {
                list.getList().add(note);
            }
        }
    }

    @Override
    public void getCash(int summa) {
        for (NoteAccount acc : getAccountList()) {
            int count = summa / acc.getNoteDenomination();
            int sumList = acc.getList().size() * acc.getNoteDenomination();
            if (count >= acc.getList().size()) {
                if (acc.getNoteDenomination() == getMinAccountDenomination() && count > acc.getList().size()) {
                    System.out.println("Недостаточно купюр нужного номинала");
                    System.exit(0);
                } else {
                    acc.getList().clear();
                    summa -= sumList;
                }
            } else if (count < acc.getList().size()) {
                for (int i = 0; i < count; i++) {
                    acc.getList().poll();
                    summa -= acc.getNoteDenomination();
                }
            }
        }
    }
}
