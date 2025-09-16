package fooddeliveryapp.domain;

import java.util.Date;
import lombok.Data;

@Data
public class OrderListQuery {

    private Long customerId;
    private Long restaurantId;
}
