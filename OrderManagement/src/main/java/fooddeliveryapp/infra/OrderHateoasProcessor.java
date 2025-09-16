package fooddeliveryapp.infra;

import fooddeliveryapp.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Order>> {

    @Override
    public EntityModel<Order> process(EntityModel<Order> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/completedelivery"
                )
                .withRel("completedelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "requestdelivery")
                .withRel("equestdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cancelorder")
                .withRel("cancelorder")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() + "/completecooking"
                )
                .withRel("completecooking")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/startdelivery")
                .withRel("startdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/acceptdelivery")
                .withRel("acceptdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/rejectdelivery")
                .withRel("rejectdelivery")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/pickupfood")
                .withRel("pickupfood")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/receiveorderbyrestaurant"
                )
                .withRel("receiveorderbyrestaurant")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "placeorder")
                .withRel("laceorder")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/startcooking")
                .withRel("startcooking")
        );

        return model;
    }
}
