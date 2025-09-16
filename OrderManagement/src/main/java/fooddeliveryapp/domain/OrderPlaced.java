package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long orderId;
    private Long customerId;
    private Long restaurantId;
    private List<Long> menuItems;
    private OrderStatus orderStatus;
    private PaymentInfo paymentInfo;
    private OrderStatus paymentStatus;
    private DeliveryAddress deliveryAddress;
    private Double orderTotalPrice;
    private Date createdAt;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
