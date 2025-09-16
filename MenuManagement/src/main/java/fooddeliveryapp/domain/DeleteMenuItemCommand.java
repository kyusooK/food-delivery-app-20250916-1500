package fooddeliveryapp.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeleteMenuItemCommand {

    private Long menuItemId;
}
