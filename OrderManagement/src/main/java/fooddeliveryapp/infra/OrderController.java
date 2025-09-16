package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/orders")
@Transactional
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(
        value = "/orders/{id}/completedelivery",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order completeDelivery(
        @PathVariable(value = "id") Long id,
        @RequestBody CompleteDeliveryCommand completeDeliveryCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/completeDelivery  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.completeDelivery(completeDeliveryCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/ordersrequestdelivery",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order requestDelivery(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestDeliveryCommand requestDeliveryCommand
    ) throws Exception {
        System.out.println("##### /order/requestDelivery  called #####");
        Order order = new Order();
        order.requestDelivery(requestDeliveryCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/cancelorder",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Order cancelOrder(
        @PathVariable(value = "id") Long id,
        @RequestBody CancelOrderCommand cancelOrderCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/cancelOrder  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.cancelOrder(cancelOrderCommand);

        orderRepository.delete(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/completecooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order completeCooking(
        @PathVariable(value = "id") Long id,
        @RequestBody CompleteCookingCommand completeCookingCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/completeCooking  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.completeCooking(completeCookingCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/startdelivery",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order startDelivery(
        @PathVariable(value = "id") Long id,
        @RequestBody StartDeliveryCommand startDeliveryCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/startDelivery  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.startDelivery(startDeliveryCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/acceptdelivery",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order acceptDelivery(
        @PathVariable(value = "id") Long id,
        @RequestBody AcceptDeliveryCommand acceptDeliveryCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/acceptDelivery  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.acceptDelivery(acceptDeliveryCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/rejectdelivery",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order rejectDelivery(
        @PathVariable(value = "id") Long id,
        @RequestBody RejectDeliveryCommand rejectDeliveryCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/rejectDelivery  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.rejectDelivery(rejectDeliveryCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/pickupfood",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order pickupFood(
        @PathVariable(value = "id") Long id,
        @RequestBody PickupFoodCommand pickupFoodCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/pickupFood  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.pickupFood(pickupFoodCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/receiveorderbyrestaurant",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order receiveOrderByRestaurant(
        @PathVariable(value = "id") Long id,
        @RequestBody ReceiveOrderByRestaurantCommand receiveOrderByRestaurantCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /order/receiveOrderByRestaurant  called #####"
        );
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.receiveOrderByRestaurant(receiveOrderByRestaurantCommand);

        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/ordersplaceorder",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Order placeOrder(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody PlaceOrderCommand placeOrderCommand
    ) throws Exception {
        System.out.println("##### /order/placeOrder  called #####");
        Order order = new Order();
        order.placeOrder(placeOrderCommand);
        orderRepository.save(order);
        return order;
    }

    @RequestMapping(
        value = "/orders/{id}/startcooking",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Order startCooking(
        @PathVariable(value = "id") Long id,
        @RequestBody StartCookingCommand startCookingCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /order/startCooking  called #####");
        Optional<Order> optionalOrder = orderRepository.findById(id);

        optionalOrder.orElseThrow(() -> new Exception("No Entity Found"));
        Order order = optionalOrder.get();
        order.startCooking(startCookingCommand);

        orderRepository.save(order);
        return order;
    }
}
//>>> Clean Arch / Inbound Adaptor
