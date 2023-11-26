package porridge.my.way.dddarchitecturej.architecture;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SequentialUUID {
    public static UUID generateUUID() {
        long timestamp = System.currentTimeMillis() * 10000;
        long randomBits = ThreadLocalRandom.current().nextLong();
        return new UUID(timestamp, randomBits);
    }

    public static long extractTimestamp(UUID sequentialUUID) {
        long timestamp = sequentialUUID.getMostSignificantBits() / 10000;
        return timestamp;
    }
}
