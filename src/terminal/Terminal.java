package terminal;

import java.util.Scanner;

public class Terminal implements ITerminalService {
    private Scanner scanner=new Scanner(System.in);
    private Account account=new Account();

    public void print(String message){
        System.out.println(message);
    }

    public void repeatChooseOperation(){
        printMenu();
        int operation=scanner.nextInt();
        choseOperation(operation);
    }

    public void printMenu() {
        print("Меню");
        print("1.Внести наличные");
        print("2.Выдача наличных");
        print("3.Доступные средства");
        print("4.Выход");
    }

    @Override
    public int enterDenamination() {
        print("Введите номинал купюр,которыми хотите пополнить баланс, и \n"+
        "вставьте купюру в приемник");
        int denomination = scanner.nextInt();
        while (denomination != account.getAccountList().get(0).getNoteDenomination()
                && denomination != account.getAccountList().get(1).getNoteDenomination()
                && denomination != account.getAccountList().get(2).getNoteDenomination()) {
            print("Купюры с данным номиналом невозможно внести! Попробуйте снова!\n" +
                    "Для выхода введите 0");
            denomination = scanner.nextInt();
            if (denomination == 0) {
                System.exit(0);
            }
        }
        return denomination;
    }

    @Override
    public int enterSummaForGetCash() {
        account.getAvailableDenomination();
        print("Введите сумму для выдачи: ");
        int summa= scanner.nextInt();
        return summa;
    }

    @Override
    public void choseOperation(int operation) {
        switch (operation) {
            case 1:
                int denomination=enterDenamination();
                account.topUpBalance(denomination);
                print("Операция выполнена");
                print("");
                break;
            case 2:
                if (account.getAccountBalance()!=0){
                int summa1=enterSummaForGetCash();
                if (summa1 % account.getMinAccountDenomination() != 0 || summa1 < 0){
                    print("Неверная сумма\n" +
                            "Выберите операцию\n" +
                            "Для выхода введите 4");
                    repeatChooseOperation();
                    break;
                }else if (summa1 > account.getAccountBalance()){
                    print("Недостаточно средств.\n" +
                            "Доступная сумма=" + account.getAccountBalance() + "\n" +
                            "Выберите операцию \n"+
                            "Для выхода введите 4");
                    repeatChooseOperation();
                    break;
                } else if (summa1 % account.getMinAvailableDenomination() != 0){
                    print("Недостаточно купюр нужного номинала.\n" +
                            "Выберите операцию\n"+
                            "Для выхода введите 4");
                    repeatChooseOperation();
                    break;
                }
                account.getCash(summa1);
                print("Операция выполнена");
                print("");
                break;
                }else {
                    print("Нет доступных средств\n"+
                    "Выберите другую операцию \n"+
                    "Для выхода введите 4");
                    repeatChooseOperation();
                    break;
                }
            case 3:
                print("Сумма доступных средств: "+account.getAccountBalance());
                break;
            case 4:
                System.exit(0);
                break;
            default:
                print("Операция не выбрана! Попробуйте снова!");
                break;
        }
    }
    public static void main(String[] args) {
        Terminal terminal=new Terminal();
        AccountWriterReader accountWriterReader=new AccountWriterReader();
        accountWriterReader.loadMapFromFile();
        while (true) {
            terminal.printMenu();
            terminal.print("Выберите операцию: ");
            int operation = terminal.scanner.nextInt();
            terminal.choseOperation(operation);
            accountWriterReader.saveMapToFile();
        }
    }
}
