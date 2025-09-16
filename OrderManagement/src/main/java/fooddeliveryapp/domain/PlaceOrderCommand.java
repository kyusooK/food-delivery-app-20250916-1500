package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceOrderCommand {

    private Long customerId;
    private Long restaurantId;
    private List<Long> menuItems;
    private PaymentInfo paymentInfo;
    private DeliveryAddress deliveryAddress;
}
