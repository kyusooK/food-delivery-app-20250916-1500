package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class MenuHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Menu>> {

    @Override
    public EntityModel<Menu> process(EntityModel<Menu> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/deletemenu")
                .withRel("deletemenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatemenu")
                .withRel("updatemenu")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "registermenu")
                .withRel("egistermenu")
        );

        return model;
    }
}
