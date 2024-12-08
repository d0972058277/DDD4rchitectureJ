package porridge.my.way.dddarchitecturej.order.controller.models;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.SpecificationValidator;
import porridge.my.way.dddarchitecturej.architecture.core.Selector;
import porridge.my.way.dddarchitecturej.order.domain.models.Price;
import porridge.my.way.dddarchitecturej.order.domain.models.Quantity;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddOrderItemRequestConstraint.Validator.class)
public @interface AddOrderItemRequestConstraint {
    String message() default "Request constraint violated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    class Validator extends SpecificationValidator<AddOrderItemRequestConstraint, AddOrderItemRequest> {
        @Override
        public boolean isValid(AddOrderItemRequest request, ConstraintValidatorContext context) {
            return isSatisfiedBy(
                    request,
                    context,
                    Price.Specification.create(Selector.set(AddOrderItemRequest::getPrice, AddOrderItemRequest.Fields.price)),
                    Quantity.Specification.create(Selector.set(AddOrderItemRequest::getQuantity, AddOrderItemRequest.Fields.quantity)));
        }
    }
}
