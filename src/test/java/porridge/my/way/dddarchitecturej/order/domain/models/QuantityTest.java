package porridge.my.way.dddarchitecturej.order.domain.models;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class QuantityTest {
    @Test
    void test_應該能夠建立一個數量() {
        // Given
        int quantityValue = 1;

        // When
        Try<Quantity> quantityTry = Quantity.create(quantityValue);

        // Then
        assertThat(quantityTry.isSuccess()).isTrue();
        assertThat(quantityTry.get().getValue()).isEqualTo(quantityValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void test_應該能夠建立一個數量失敗的情況(int quantityValue) {
        // Given

        // When
        Try<Quantity> quantityTry = Quantity.create(quantityValue);

        // Then
        assertThat(quantityTry.isFailure()).isTrue();
    }
}