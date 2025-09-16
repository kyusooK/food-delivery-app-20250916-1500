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
// @RequestMapping(value="/menus")
@Transactional
public class MenuController {

    @Autowired
    MenuRepository menuRepository;

    @RequestMapping(
        value = "/menus/{id}/deletemenu",
        method = RequestMethod.DELETE,
        produces = "application/json;charset=UTF-8"
    )
    public Menu deleteMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody DeleteMenuCommand deleteMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /menu/deleteMenu  called #####");
        Optional<Menu> optionalMenu = menuRepository.findById(id);

        optionalMenu.orElseThrow(() -> new Exception("No Entity Found"));
        Menu menu = optionalMenu.get();
        menu.deleteMenu(deleteMenuCommand);

        menuRepository.delete(menu);
        return menu;
    }

    @RequestMapping(
        value = "/menus/{id}/updatemenu",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Menu updateMenu(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateMenuCommand updateMenuCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /menu/updateMenu  called #####");
        Optional<Menu> optionalMenu = menuRepository.findById(id);

        optionalMenu.orElseThrow(() -> new Exception("No Entity Found"));
        Menu menu = optionalMenu.get();
        menu.updateMenu(updateMenuCommand);

        menuRepository.save(menu);
        return menu;
    }

    @RequestMapping(
        value = "/menusregistermenu",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Menu registerMenu(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RegisterMenuCommand registerMenuCommand
    ) throws Exception {
        System.out.println("##### /menu/registerMenu  called #####");
        Menu menu = new Menu();
        menu.registerMenu(registerMenuCommand);
        menuRepository.save(menu);
        return menu;
    }
}
//>>> Clean Arch / Inbound Adaptor
