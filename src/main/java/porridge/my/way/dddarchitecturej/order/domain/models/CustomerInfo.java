package porridge.my.way.dddarchitecturej.order.domain.models;

import java.util.List;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.core.ValueObject;

public class CustomerInfo extends ValueObject {
    @Getter
    private String name;
    @Getter
    private String address;

    private CustomerInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static CustomerInfo create(String name, String address) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Parameter 'name' cannot be null or empty");
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Parameter 'address' cannot be null or empty");

        return new CustomerInfo(name, address);
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(name, address);
    }
}
