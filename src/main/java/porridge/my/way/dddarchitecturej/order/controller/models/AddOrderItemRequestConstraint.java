package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;
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
    class Validator implements ConstraintValidator<AddOrderItemRequestConstraint, AddOrderItemRequest> {
        @Override
        public boolean isValid(AddOrderItemRequest value, ConstraintValidatorContext context) {
            Try<Price> priceTry = Price.create(value.getPrice());
            if (priceTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(priceTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }

            Try<Quantity> quantityTry = Quantity.create(value.getQuantity());
            if (quantityTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(quantityTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }

            return true;
        }
    }
}
