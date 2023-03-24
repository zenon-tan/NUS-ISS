package accounts;

public class FixedDeposit implements IInterest, IInformation {

    private float balance;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Float interest() {
        return this.balance * 0.02f;
    }

    @Override
    public float calculateInterest() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String name() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getInformation() {
        // TODO Auto-generated method stub
        return "this is some information";
    }

    
}
