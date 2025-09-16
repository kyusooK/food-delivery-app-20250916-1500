package fooddeliveryapp.domain;

import fooddeliveryapp.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "menuItems", path = "menuItems")
public interface MenuItemRepository
    extends PagingAndSortingRepository<MenuItem, Long> {
    @Query(
        value = "select menuItem " +
        "from MenuItem menuItem " +
        "where(:menuItemId is null or menuItem.menuItemId = :menuItemId)"
    )
    MenuItem menuItemDetails(Long menuItemId);

    @Query(
        value = "select menuItem " +
        "from MenuItem menuItem " +
        "where(:menuId is null or menuItem.menuId = :menuId)"
    )
    List<MenuItem> menuItemList(Long menuId, Pageable pageable);
}
