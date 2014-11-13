package organizer;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException("Private constructor should never be invoked");
    }

    public static final int DEFAULT_RESULTS_LIMIT = 10;
    public static final String DEFAULT_RESULTS_LIMIT_STRING = "10";

    public static final int CACHE_TTL = 3600;
}
