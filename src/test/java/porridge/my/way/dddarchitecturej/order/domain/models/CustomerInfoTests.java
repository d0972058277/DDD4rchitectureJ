package porridge.my.way.dddarchitecturej.order.domain.models;

import an.awesome.pipelinr.repack.com.google.common.base.Supplier;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomerInfoTests {
    static Stream<String> failureParametersProvider() {
        return Stream.of(null, "", " ");
    }

    @SneakyThrows
    private CustomerInfo createCustomerInfo(String address, String name) {
        return CustomerInfo.create(name, address);
    }

    @SneakyThrows
    @Test
    public void test_應該能夠成功建立() {
        // Given
        String name = "name";
        String address = "adress";

        // When
        CustomerInfo customerInfo = createCustomerInfo(address, name);

        // Then
        assertThat(customerInfo.getName()).isEqualTo(name);
        assertThat(customerInfo.getAddress()).isEqualTo(address);
    }

    @ParameterizedTest
    @MethodSource("failureParametersProvider")
    public void test_name為空或空字串或空白_建立時應該拋出例外(String name) {
        // Given
        String address = "adress";

        // When
        Supplier<CustomerInfo> supplier = () -> createCustomerInfo(address, name);

        // Then
        assertThatThrownBy(supplier::get).isInstanceOf(IllegalArgumentDomainException.class);
    }

    @ParameterizedTest
    @MethodSource("failureParametersProvider")
    public void test_address為空或空字串或空白_建立時應該拋出例外(String address) {
        // Given
        String name = "name";

        // When
        Supplier<CustomerInfo> supplier = () -> createCustomerInfo(address, name);

        // Then
        assertThatThrownBy(supplier::get).isInstanceOf(IllegalArgumentDomainException.class);
    }
}
