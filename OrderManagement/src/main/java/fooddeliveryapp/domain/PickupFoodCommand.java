package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PickupFoodCommand {

    private Long orderId;
    private Long deliveryPersonId;
}
