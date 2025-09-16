package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class StartDeliveryCommand {

    private Long orderId;
    private Long deliveryPersonId;
}
