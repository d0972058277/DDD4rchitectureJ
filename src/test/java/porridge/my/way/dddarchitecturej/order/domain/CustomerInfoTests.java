package porridge.my.way.dddarchitecturej.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import an.awesome.pipelinr.repack.com.google.common.base.Supplier;

public class CustomerInfoTests {
    @Test
    public void test_應該能夠成功建立() {
        // Given
        String name = "name";
        String address = "adress";

        // When
        CustomerInfo customerInfo = CustomerInfo.create(name, address);

        // Then
        assertThat(customerInfo.name).isEqualTo(name);
        assertThat(customerInfo.address).isEqualTo(address);
    }

    static Stream<String> failureParametersProvider() {
        return Stream.of(null, "", " ");
    }

    @ParameterizedTest
    @MethodSource("failureParametersProvider")
    public void test_name為空或空字串或空白_建立時應該拋出例外(String name) {
        // Given
        String address = "adress";

        // When
        Supplier<CustomerInfo> supplier = () -> CustomerInfo.create(name, address);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("failureParametersProvider")
    public void test_address為空或空字串或空白_建立時應該拋出例外(String address) {
        // Given
        String name = "name";

        // When
        Supplier<CustomerInfo> supplier = () -> CustomerInfo.create(name, address);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentException.class);
    }
}
