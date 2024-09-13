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
public class CustomerInfo extends ValueObject {
    private String name;
    private String address;

    private CustomerInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static Try<CustomerInfo> create(String name, String address) {
        Specification<CustomerInfo> specification = Specification.create(Selector.set(CustomerInfo::getName, Fields.name), Selector.set(CustomerInfo::getAddress, Fields.address));
        CustomerInfo customerInfo = new CustomerInfo(name, address);
        return specification.isSatisfiedBy(customerInfo);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(name, address);
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Specification<T> extends SpecificationBase<T> {
        private final ISelector<T, String> nameSelector;
        private final ISelector<T, String> addressSelector;

        public static <T> Specification<T> create(ISelector<T, String> nameSelector, ISelector<T, String> addressSelector) {
            return new Specification<>(nameSelector, addressSelector);
        }

        @Override
        protected List<SpecificationRule<T>> getSpecificationRules() {
            return List.of(
                    new SpecificationRule<>(
                            String.format("Parameter '%s' cannot be null or empty", nameSelector.getPropertyName()),
                            arg -> nameSelector.getValue(arg) != null && !nameSelector.getValue(arg).isBlank()),
                    new SpecificationRule<>(
                            String.format("Parameter '%s' cannot be null or empty", addressSelector.getPropertyName()),
                            arg -> addressSelector.getValue(arg) != null && !addressSelector.getValue(arg).isBlank())
            );
        }
    }
}
