package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
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
            Try<CustomerInfo> customerInfoTry = CustomerInfo.create(request.getName(), request.getAddress());
            
            if (customerInfoTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(customerInfoTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }

            return true;
        }
    }
}
