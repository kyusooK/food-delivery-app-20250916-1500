package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuUpdated extends AbstractEvent {

    private Long menuId;
    private String name;
    private String description;
    private MenuStatus status;
    private Date updatedAt;

    public MenuUpdated(Menu aggregate) {
        super(aggregate);
    }

    public MenuUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
