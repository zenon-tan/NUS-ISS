package day21.workshop.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private Date orderDate;
    private Date shippedDate;
    private String shipName;
    private String shipAddress;
    private String shipStateProvince;
    
}
