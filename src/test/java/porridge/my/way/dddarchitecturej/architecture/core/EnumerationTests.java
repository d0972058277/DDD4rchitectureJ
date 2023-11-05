package porridge.my.way.dddarchitecturej.architecture.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SampleEnumeration extends Enumeration {

    protected SampleEnumeration(int id, String name) {
        super(id, name);
    }

    public static SampleEnumeration First() {
        return new SampleEnumeration(1, "First");
    }
}

public class EnumerationTests {
    @Test
    public void test_() {
        // Given
        var enumeration = SampleEnumeration.First();

        // When

        // Then
        assertThat(enumeration).isNotNull();
        assertThat(enumeration.getId()).isEqualTo(1);
        assertThat(enumeration.getName()).isEqualTo("First");
    }
}
