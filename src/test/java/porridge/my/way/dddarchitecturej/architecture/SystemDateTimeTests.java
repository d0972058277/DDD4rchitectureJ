package porridge.my.way.dddarchitecturej.architecture;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class SystemDateTimeTests {
    @Test
    public void testGetUtcNowAfterInit() throws InterruptedException {
        // Given
        SystemDateTime.initUtcNow(() -> new Date());

        // When
        var utcNow1 = SystemDateTime.getUtcNow();
        Thread.sleep(1);
        var utcNow2 = SystemDateTime.getUtcNow();

        // Then
        assertThat(utcNow1).isBefore(new Date());
        assertThat(utcNow1).isNotEqualTo(utcNow2);
    }
}
