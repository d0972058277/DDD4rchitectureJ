package porridge.my.way.dddarchitecturej.order.controller.models;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddOrderItemRequestConstraint.Validator.class)
public @interface AddOrderItemRequestConstraint {
    String message() default "Request constraint violated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<AddOrderItemRequestConstraint, AddOrderItemRequest> {
        @Override
        public boolean isValid(AddOrderItemRequest value, ConstraintValidatorContext context) {
            try {
                OrderItem.create(value.getProductId(), value.getPrice(), value.getQuantity());
                return true;
            } catch (IllegalArgumentDomainException e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
                return false;
            }
        }
    }
}
