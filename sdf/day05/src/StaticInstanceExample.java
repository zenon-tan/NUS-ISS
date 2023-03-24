
public class StaticInstanceExample {

    public static void main(String[] args) { // this is a static method because this class isn't instantiated when starting, hence it'll use a static method

        // Static makes the member or method global (and accessible if its public)
        // Access static member without instantiating the class (It is a class member)
        // Or used as a singleton pattern (we only want a single instance of a class aka it can only be created once)
        // A global copy

        Item.quantity = 100;
        Item.printQuantity();

        // When you don't want a variable to be changed, use final
        // final classes cannot be extended into a subclass
        // final methods cannot be overriden e.g. methods for calculation of interest -> when you don't want subclasses to alter it
        // Usually used for constants
        
        final int version = 10;
    
        //version = 10;

        //non-static methods and members are instance members
        //Item.name = "fff";

        Item apple = new Item();
        apple.setName("apple");
        // apple.setQuantity(10);

        Item orange = new Item();
        orange.setName("orange");
        // orange.setQuantity(3);
        // Setting 'static' to a method or member that you want to share across ALL instances
        // static methods and members can be used WITHOUT initialisation or instantiation

        System.out.println(apple.getName());
        System.out.println(orange.getName());
        System.out.println(apple.getQuantity());
        System.out.println(orange.getQuantity());
    }
}