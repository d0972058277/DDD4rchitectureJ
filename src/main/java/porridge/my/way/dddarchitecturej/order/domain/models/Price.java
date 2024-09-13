package porridge.my.way.dddarchitecturej.order.domain.models;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import porridge.my.way.dddarchitecturej.architecture.core.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@FieldNameConstants
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price extends ValueObject {
    private BigDecimal value;

    private Price(BigDecimal value) {
        this.value = value;
    }

    public static Try<Price> create(BigDecimal value) {
        Specification<Price> specification = Specification.create(Selector.set(Price::getValue, Fields.value));
        Price price = new Price(value);
        return specification.isSatisfiedBy(price);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(value);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Specification<T> extends SpecificationBase<T> {
        private final ISelector<T, BigDecimal> valueSelector;

        public static <T> Specification<T> create(ISelector<T, BigDecimal> valueSelector) {
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
                            arg -> valueSelector.getValue(arg).compareTo(BigDecimal.ZERO) > 0)
            );
        }
    }
}
