package accounts;

public interface IInterest {

    public float calculateInterest();
    public String name();

    // Declare the methods in here but without implementation
    // Implementing an interface methods getting into an agreement with it: it MUST provide ALL methods defined in the interface
    // You are technically overriding the methods in the interface
    // Any object that implements the interest interface
    // a class can implement multiple interfaces
    // adding interfaces into a class is called composition, as opposed to inheritance where you have to take everything from the parent class
    
    
}
