package porridge.my.way.dddarchitecturej.architecture;

import java.security.SecureRandom;
import java.util.UUID;

public class SequentialUUID {
    private static final SecureRandom random = new SecureRandom();

    public static UUID generateUUID() {
        long timestamp = System.currentTimeMillis();
        return new UUID(timestamp, random.nextLong());
    }

    public static long extractTimestamp(UUID sequentialUUID) {
        long timestamp = sequentialUUID.getMostSignificantBits();
        return timestamp;
    }
}
