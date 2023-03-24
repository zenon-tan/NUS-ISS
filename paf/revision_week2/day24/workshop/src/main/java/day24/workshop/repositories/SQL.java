package day24.workshop.repositories;

public class SQL {

    public static final String SQL_INSERT_ORDER = """

    insert into orders(order_id, order_date, customer_name, ship_address, notes, tax)
    values(?, ?, ?, ?, ?, ?)
            
    """;

    public static final String SQL_INSERT_ORDER_DETAILS = """

    insert into order_details(product, unit_price, discount, order_id, quantity)
    values(?, ?, ?, ?, ?)
            
    """;

    public static final String SQL_GET_ORDER_BY_ID = """
            select * from orders where order_id = ?
            """;

    public static final String SQL_GET_ITEMS_BY_ID = """
    select * from order_details where order_id = ?
                """;

    
    
}
