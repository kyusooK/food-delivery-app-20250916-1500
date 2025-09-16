package fooddeliveryapp.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryapp.MenuManagementApplication;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MenuItem_table")
@Data
//<<< DDD / Aggregate Root
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuItemId;

    private Long menuId;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private MenuItemStatus status;

    private Date createdAt;

    private Date updatedAt;

    public static MenuItemRepository repository() {
        MenuItemRepository menuItemRepository = MenuManagementApplication.applicationContext.getBean(
            MenuItemRepository.class
        );
        return menuItemRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateMenuItem(UpdateMenuItemCommand updateMenuItemCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void deleteMenuItem(DeleteMenuItemCommand deleteMenuItemCommand) {
        //implement business logic here:

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void registerMenuItem(
        RegisterMenuItemCommand registerMenuItemCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
