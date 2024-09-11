package porridge.my.way.dddarchitecturej.architecture.core;

import io.vavr.control.Try;

import java.util.List;

public abstract class SpecificationBase<T> {
    private List<SpecificationRule<T>> specificationRules;

    protected abstract List<SpecificationRule<T>> getSpecificationRules();

    public Try<T> isSatisfiedBy(T entity) {
        for (SpecificationRule<T> rule : getRules()) {
            if (!rule.getValidate().test(entity)) {
                return Try.failure(new IllegalArgumentException(rule.getMessage()));
            }
        }

        return Try.success(entity);
    }

    public List<SpecificationRule<T>> getRules() {
        if (specificationRules == null) specificationRules = getSpecificationRules().stream().toList();
        return specificationRules;
    }
}
