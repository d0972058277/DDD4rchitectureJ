package porridge.my.way.dddarchitecturej.architecture.core;

import io.vavr.control.Try;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecificationBase<T> {
    private volatile List<SpecificationRule<T>> specificationRules;

    protected abstract List<SpecificationRule<T>> getSpecificationRules();

    public Try<T> isSatisfiedBy(T entity) {
        for (SpecificationRule<T> rule : getRules()) {
            if (!rule.getValidate().test(entity)) {
                return Try.failure(new IllegalArgumentDomainException(rule.getMessage()));
            }
        }

        return Try.success(entity);
    }

    public List<SpecificationRule<T>> getRules() {
        if (specificationRules == null) {
            synchronized (this) {
                if (specificationRules == null) {
                    specificationRules = new ArrayList<>(getSpecificationRules());
                }
            }
        }
        return specificationRules;
    }
}
