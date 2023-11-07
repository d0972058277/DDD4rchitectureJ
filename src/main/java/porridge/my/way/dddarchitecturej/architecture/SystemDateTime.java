package porridge.my.way.dddarchitecturej.architecture;

import java.util.Date;
import java.util.Objects;

public class SystemDateTime {
    private static UtcNowProvider utcNowProvider;

    public static Date getUtcNow() {
        if (utcNowProvider == null) {
            throw new IllegalStateException(
                    "SystemDateTime has not been initialized. Please use SystemDateTime.initUtcNow() to initialize when the program starts.");
        }
        return utcNowProvider.getUtcNow();
    }

    public static void initUtcNow(UtcNowProvider provider) {
        utcNowProvider = Objects.requireNonNull(provider, "UtcNowProvider cannot be null");
    }

    @FunctionalInterface
    public interface UtcNowProvider {
        Date getUtcNow();
    }
}
