package porridge.my.way.dddarchitecturej.architecture.core;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SampleEntity extends Entity<Integer> {
    SampleEntity(Integer id) {
        super(id);
    }
}

public class EntityTests {
    @Test
    public void testEquals() {
        // Given
        var entity1 = new SampleEntity(1);
        var entity2 = new SampleEntity(2);
        var entity3 = new SampleEntity(1);

        // When

        // Then
        assertThat(entity1).isEqualTo(entity1);
        assertThat(entity1).isEqualTo(entity3);
        assertThat(entity3).isEqualTo(entity1);
        assertThat(entity1).isNotEqualTo(entity2);
        assertThat(entity1).isNotEqualTo(null);
        assertThat(entity1).isNotEqualTo(new Object());
    }

    @Test
    public void testHashCode() {
        // Given
        var entity1 = new SampleEntity(1);
        var entity2 = new SampleEntity(2);
        var entity3 = new SampleEntity(1);

        // When

        // Then
        assertThat(entity1.hashCode()).isEqualTo(entity3.hashCode());
        assertThat(entity1.hashCode()).isNotEqualTo(entity2.hashCode());
    }

    @Test
    public void testCompareTo() {
        // Given
        var entity1 = new SampleEntity(1);
        var entity2 = new SampleEntity(2);
        var entity3 = new SampleEntity(1);

        // When

        // Then
        assertThat(entity1.compareTo(entity2)).isLessThan(0);
        assertThat(entity2.compareTo(entity1)).isGreaterThan(0);
        assertThat(entity1.compareTo(entity3)).isEqualTo(0);
    }
}