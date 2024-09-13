package porridge.my.way.dddarchitecturej.order.domain.models;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PriceTest {
    @Test
    void test_應該能夠建立一個價格() {
        // Given
        BigDecimal priceValue = BigDecimal.ONE;

        // When
        Try<Price> price = Price.create(priceValue);

        // Then
        assertThat(price.isSuccess()).isTrue();
        assertThat(price.get().getValue()).isEqualTo(priceValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    @NullSource
    void test_應該能夠建立一個價格失敗的情況(BigDecimal priceValue) {
        // Given

        // When
        Try<Price> price = Price.create(priceValue);

        // Then
        assertThat(price.isFailure()).isTrue();
    }
}