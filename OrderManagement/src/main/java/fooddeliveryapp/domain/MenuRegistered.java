package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuRegistered extends AbstractEvent {

    private Long menuId;
    private Long restaurantId;
    private String name;
    private String description;
    private Object status;
    private Date createdAt;
}
