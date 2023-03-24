package accounts;

public class CalcInterest {

    public static void main(String[] args) {
        
        Account account = new Account();
        account.setBalance(1000);

        FixedDeposit fixed = new FixedDeposit();
        fixed.setBalance(1000);

        calculateInterest(account);
        calculateInterest(fixed);

        printInfo(account);
        printInfo(fixed);
    }



    public static void calculateInterest(IInterest intf) {
        // Very complicated calculation
        System.out.println(intf.calculateInterest());

    }

    public static void printInfo(IInformation info) {
        System.out.println(info.getInformation());
    }
    
}
