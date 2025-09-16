package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/menuItems")
@Transactional
public class MenuItemController {

    @Autowired
    MenuItemRepository menuItemRepository;

    @RequestMapping(
        value = "/menuItems/{id}/updatemenuitem",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public MenuItem updateMenuItem(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateMenuItemCommand updateMenuItemCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /menuItem/updateMenuItem  called #####");
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);

        optionalMenuItem.orElseThrow(() -> new Exception("No Entity Found"));
        MenuItem menuItem = optionalMenuItem.get();
        menuItem.updateMenuItem(updateMenuItemCommand);

        menuItemRepository.save(menuItem);
        return menuItem;
    }

    @RequestMapping(
        value = "/menuItems/{id}/deletemenuitem",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public MenuItem deleteMenuItem(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteMenuItemCommand deleteMenuItemCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /menuItem/deleteMenuItem  called #####");
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);

        optionalMenuItem.orElseThrow(() -> new Exception("No Entity Found"));
        MenuItem menuItem = optionalMenuItem.get();
        menuItem.deleteMenuItem(deleteMenuItemCommand);

        menuItemRepository.delete(menuItem);
        return menuItem;
    }

    @RequestMapping(
        value = "/menuItemsregistermenuitem",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public MenuItem registerMenuItem(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterMenuItemCommand registerMenuItemCommand
    ) throws Exception {
        System.out.println("##### /menuItem/registerMenuItem  called #####");
        MenuItem menuItem = new MenuItem();
        menuItem.registerMenuItem(registerMenuItemCommand);
        menuItemRepository.save(menuItem);
        return menuItem;
    }
}
//>>> Clean Arch / Inbound Adaptor
