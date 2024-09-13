package porridge.my.way.dddarchitecturej;

import io.vavr.control.Try;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import porridge.my.way.dddarchitecturej.architecture.core.SpecificationBase;

import java.lang.annotation.Annotation;

public abstract class SpecificationValidator<RequestConstraint extends Annotation, Request> implements ConstraintValidator<RequestConstraint, Request> {
    @SafeVarargs
    protected final boolean isSatisfiedBy(Request request, ConstraintValidatorContext context, @NotNull SpecificationBase<Request>... specifications) {
        for (SpecificationBase<Request> specification : specifications) {
            Try<Request> requestTry = specification.isSatisfiedBy(request);
            if (requestTry.isFailure()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(requestTry.getCause().getMessage()).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
