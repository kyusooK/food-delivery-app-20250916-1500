package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuDeleted extends AbstractEvent {

    private Long menuId;
    private MenuStatus status;
    private Date updatedAt;

    public MenuDeleted(Menu aggregate) {
        super(aggregate);
    }

    public MenuDeleted() {
        super();
    }
}
//>>> DDD / Domain Event
