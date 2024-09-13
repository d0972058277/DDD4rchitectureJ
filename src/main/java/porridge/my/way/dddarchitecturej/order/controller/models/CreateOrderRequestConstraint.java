package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.core.Selector;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreateOrderRequestConstraint.Validator.class)
public @interface CreateOrderRequestConstraint {
    String message() default "Request constraint violated";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Component
    class Validator implements ConstraintValidator<CreateOrderRequestConstraint, CreateOrderRequest> {
        @Override
        public boolean isValid(CreateOrderRequest request, ConstraintValidatorContext context) {
            CustomerInfo.Specification<CreateOrderRequest> specification = CustomerInfo.Specification.create(
                    Selector.set(CreateOrderRequest::getName, CreateOrderRequest.Fields.name),
                    Selector.set(CreateOrderRequest::getAddress, CreateOrderRequest.Fields.address));
            Try<CreateOrderRequest> createOrderRequestTry = specification.isSatisfiedBy(request);

            if (createOrderRequestTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(createOrderRequestTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }

            return true;
        }
    }
}
