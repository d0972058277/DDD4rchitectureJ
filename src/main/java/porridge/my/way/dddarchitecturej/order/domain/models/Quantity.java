package porridge.my.way.dddarchitecturej.order.domain.models;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import porridge.my.way.dddarchitecturej.architecture.core.*;

import java.util.List;

@Getter
@FieldNameConstants
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quantity extends ValueObject {
    private int value;

    private Quantity(int value) {
        this.value = value;
    }

    public static Try<Quantity> create(int value) {
        Specification<Quantity> specification = Specification.create(Selector.set(Quantity::getValue, Fields.value));
        Quantity quantity = new Quantity(value);
        return specification.isSatisfiedBy(quantity);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(value);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Specification<T> extends SpecificationBase<T> {
        private final ISelector<T, Integer> valueSelector;

        public static <T> Specification<T> create(ISelector<T, Integer> valueSelector) {
            return new Specification<>(valueSelector);
        }

        @Override
        protected List<SpecificationRule<T>> getSpecificationRules() {
            return List.of(
                    new SpecificationRule<>(
                            String.format("'%s' must not be null", valueSelector.getPropertyName()),
                            arg -> valueSelector.getValue(arg) != null),
                    new SpecificationRule<>(
                            String.format("'%s' must be positive", valueSelector.getPropertyName()),
                            arg -> valueSelector.getValue(arg) > 0)
            );
        }
    }
}
