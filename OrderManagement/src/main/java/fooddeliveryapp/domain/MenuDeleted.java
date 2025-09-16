package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuDeleted extends AbstractEvent {

    private Long menuId;
    private Object status;
    private Date updatedAt;
}
