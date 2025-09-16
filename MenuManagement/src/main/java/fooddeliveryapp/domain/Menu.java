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
@Table(name = "Menu_table")
@Data
//<<< DDD / Aggregate Root
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long menuId;

    private Long restaurantId;

    private String name;

    private String description;

    private MenuStatus status;

    private Date createdAt;

    private Date updatedAt;

    private MenuItemId menuItemId;

    public static MenuRepository repository() {
        MenuRepository menuRepository = MenuManagementApplication.applicationContext.getBean(
            MenuRepository.class
        );
        return menuRepository;
    }

    //<<< Clean Arch / Port Method
    public void deleteMenu(DeleteMenuCommand deleteMenuCommand) {
        //implement business logic here:

        MenuDeleted menuDeleted = new MenuDeleted(this);
        menuDeleted.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateMenu(UpdateMenuCommand updateMenuCommand) {
        //implement business logic here:

        MenuUpdated menuUpdated = new MenuUpdated(this);
        menuUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void registerMenu(RegisterMenuCommand registerMenuCommand) {
        //implement business logic here:

        MenuRegistered menuRegistered = new MenuRegistered(this);
        menuRegistered.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
