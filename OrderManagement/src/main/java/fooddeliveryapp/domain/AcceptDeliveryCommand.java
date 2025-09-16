package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AcceptDeliveryCommand {

    private Long orderId;
    private Long deliveryPersonId;
}
