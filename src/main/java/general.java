import java.util.Objects;

public class general extends News {
    private final String NEWS_TYPE = "general news";
    public general(String title, String content) {
        super(title, content);
        type = NEWS_TYPE;
    }

    public String getNEWS_TYPE() {
        return NEWS_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof general)) return false;
        general general = (general) o;
        return Objects.equals(NEWS_TYPE, general.NEWS_TYPE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NEWS_TYPE);
    }
}
