package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porridge.my.way.dddarchitecturej.architecture.core.ValueObject;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerInfo extends ValueObject {
    private String name;
    private String address;

    private CustomerInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static CustomerInfo create(String name, String address) throws IllegalArgumentDomainException {
        if (name == null || name.isBlank())
            throw new IllegalArgumentDomainException("Parameter 'name' cannot be null or empty");
        if (address == null || address.isBlank())
            throw new IllegalArgumentDomainException("Parameter 'address' cannot be null or empty");

        return new CustomerInfo(name, address);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(name, address);
    }
}
