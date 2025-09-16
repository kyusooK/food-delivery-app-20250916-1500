package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CompleteDeliveryCommand {

    private Long orderId;
    private Long deliveryPersonId;
    private Boolean customerConfirmation;
}
