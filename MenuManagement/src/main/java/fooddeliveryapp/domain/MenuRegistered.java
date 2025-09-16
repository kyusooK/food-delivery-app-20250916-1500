package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuRegistered extends AbstractEvent {

    private Long menuId;
    private Long restaurantId;
    private String name;
    private String description;
    private MenuStatus status;
    private Date createdAt;

    public MenuRegistered(Menu aggregate) {
        super(aggregate);
    }

    public MenuRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
