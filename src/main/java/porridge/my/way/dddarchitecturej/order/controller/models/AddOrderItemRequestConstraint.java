package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;
import porridge.my.way.dddarchitecturej.order.domain.models.Price;

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

            try {
                OrderItem.create(value.getProductId(), priceTry.get(), value.getQuantity());
                return true;
            } catch (IllegalArgumentDomainException e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
                return false;
            }
        }
    }
}
