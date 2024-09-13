package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.stereotype.Component;
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
    class Validator implements ConstraintValidator<AddOrderItemRequestConstraint, AddOrderItemRequest> {
        @Override
        public boolean isValid(AddOrderItemRequest request, ConstraintValidatorContext context) {
            Price.Specification<AddOrderItemRequest> priceSpecification = Price.Specification.create(
                    Selector.set(AddOrderItemRequest::getPrice, AddOrderItemRequest.Fields.price));
            Try<AddOrderItemRequest> priceSpecificationTry = priceSpecification.isSatisfiedBy(request);
            if (priceSpecificationTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(priceSpecificationTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }

            Quantity.Specification<AddOrderItemRequest> quantitySpecification = Quantity.Specification.create(
                    Selector.set(AddOrderItemRequest::getQuantity, AddOrderItemRequest.Fields.quantity));
            Try<AddOrderItemRequest> quantitySpecificationTry = quantitySpecification.isSatisfiedBy(request);
            if (quantitySpecificationTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(quantitySpecificationTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }
            
            return true;
        }
    }
}
