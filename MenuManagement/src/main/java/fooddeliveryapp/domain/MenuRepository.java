package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "menus", path = "menus")
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {
    @Query(
        value = "select menu " +
        "from Menu menu " +
        "where(:restaurantId is null or menu.restaurantId = :restaurantId)"
    )
    List<Menu> menuList(Long restaurantId, Pageable pageable);

    @Query(
        value = "select menu " +
        "from Menu menu " +
        "where(:menuId is null or menu.menuId = :menuId)"
    )
    Menu menuDetails(Long menuId);
}
