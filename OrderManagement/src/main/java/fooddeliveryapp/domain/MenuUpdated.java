package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import fooddeliveryapp.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class MenuUpdated extends AbstractEvent {

    private Long menuId;
    private String name;
    private String description;
    private Object status;
    private Date updatedAt;
}
