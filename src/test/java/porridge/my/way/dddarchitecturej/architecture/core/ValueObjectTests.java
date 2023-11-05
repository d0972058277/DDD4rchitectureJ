package porridge.my.way.dddarchitecturej.architecture.core;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import lombok.Getter;

class Address extends ValueObject {
    @Getter
    private String street;
    @Getter
    private String city;
    @Getter
    private String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(street, city, zipCode);
    }
}

public class ValueObjectTests {
    @Test
    public void testAddressEquality() {
        // Given
        Address address1 = new Address("123 Main St", "City1", "12345");
        Address address2 = new Address("123 Main St", "City1", "12345");
        Address address3 = new Address("456 Elm St", "City2", "54321");

        // When

        // Then
        assertThat(address1).isEqualTo(address2);
        assertThat(address1).isNotEqualTo(address3);
    }

    @Test
    public void testAddressHashCode() {
        // Given
        Address address1 = new Address("123 Main St", "City1", "12345");
        Address address2 = new Address("123 Main St", "City1", "12345");
        Address address3 = new Address("456 Elm St", "City2", "54321");

        // When

        // Then
        assertThat(address1.hashCode()).isEqualTo(address2.hashCode());
        assertThat(address1.hashCode()).isNotEqualTo(address3.hashCode());
    }
}
