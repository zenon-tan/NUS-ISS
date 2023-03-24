public class Item {

    private String name;
    // Members shared by the class "static"
    public static Integer quantity;
    // When you don't want a variable to be changed, use final
    // final classes cannot be extended into a subclass


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Integer getQuantity() {
        return quantity;
    }

    public static void setQuantity(Integer quantity) {
        Item.quantity = quantity;
    }

    public static void printQuantity() {
        System.out.println(">>> Quantity is " + quantity);
    }

    

    
    
}
