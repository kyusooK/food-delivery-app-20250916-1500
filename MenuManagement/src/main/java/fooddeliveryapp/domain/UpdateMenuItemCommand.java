package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateMenuItemCommand {

    private Long menuItemId;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
}
