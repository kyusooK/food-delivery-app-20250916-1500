package fooddeliveryapp.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryapp.OrderManagementApplication;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private Long customerId;

    private Long restaurantId;

    @ElementCollection
    private List<Long> menuItems;

    private OrderStatus orderStatus;

    private PaymentInfo paymentInfo;

    private OrderStatus paymentStatus;

    private DeliveryAddress deliveryAddress;

    private DeliveryStatus deliveryStatus;

    private DeliveryPersonInfo deliveryPersonInfo;

    private String deliveryRoute;

    private String customerContact;

    private String restaurantContact;

    private Double orderTotalPrice;

    private Date createdAt;

    private Date updatedAt;

    private Long version;

    private MenuId menuId;

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderManagementApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    //<<< Clean Arch / Port Method
    public void completeDelivery(
        CompleteDeliveryCommand completeDeliveryCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void requestDelivery(RequestDeliveryCommand requestDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancelOrder(CancelOrderCommand cancelOrderCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void completeCooking(CompleteCookingCommand completeCookingCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void startDelivery(StartDeliveryCommand startDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void acceptDelivery(AcceptDeliveryCommand acceptDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void rejectDelivery(RejectDeliveryCommand rejectDeliveryCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void pickupFood(PickupFoodCommand pickupFoodCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void receiveOrderByRestaurant(
        ReceiveOrderByRestaurantCommand receiveOrderByRestaurantCommand
    ) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void placeOrder(PlaceOrderCommand placeOrderCommand) {
        //implement business logic here:

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void startCooking(StartCookingCommand startCookingCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void syncMenuOnRegistered(MenuRegistered menuRegistered) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderPlaced orderPlaced = new OrderPlaced(order);
        orderPlaced.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if menuRegistered.menuItemId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> menuMap = mapper.convertValue(menuRegistered.getMenuItemId(), Map.class);

        repository().findById(menuRegistered.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderPlaced orderPlaced = new OrderPlaced(order);
            orderPlaced.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void syncMenuOnDeleted(MenuDeleted menuDeleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderPlaced orderPlaced = new OrderPlaced(order);
        orderPlaced.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if menuDeleted.menuItemId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> menuMap = mapper.convertValue(menuDeleted.getMenuItemId(), Map.class);

        repository().findById(menuDeleted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderPlaced orderPlaced = new OrderPlaced(order);
            orderPlaced.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void syncMenuOnUpdated(MenuUpdated menuUpdated) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderPlaced orderPlaced = new OrderPlaced(order);
        orderPlaced.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if menuUpdated.menuItemId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> menuMap = mapper.convertValue(menuUpdated.getMenuItemId(), Map.class);

        repository().findById(menuUpdated.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderPlaced orderPlaced = new OrderPlaced(order);
            orderPlaced.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
