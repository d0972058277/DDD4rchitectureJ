package porridge.my.way.dddarchitecturej.order.controller.models;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreateOrderRequestConstraint.Validator.class)
public @interface CreateOrderRequestConstraint {
    class Validator implements ConstraintValidator<CreateOrderRequestConstraint, CreateOrderRequest> {
        @Override
        public boolean isValid(CreateOrderRequest request, ConstraintValidatorContext context) {
            try {
                CustomerInfo.create(request.getName(), request.getAddress());
                return true;
            } catch (IllegalArgumentDomainException e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage()).addConstraintViolation();
                return false;
            }
        }
    }
}
