package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class MenuItemHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<MenuItem>> {

    @Override
    public EntityModel<MenuItem> process(EntityModel<MenuItem> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatemenuitem")
                .withRel("updatemenuitem")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletemenuitem")
                .withRel("deletemenuitem")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "registermenuitem"
                )
                .withRel("egistermenuitem")
        );

        return model;
    }
}
