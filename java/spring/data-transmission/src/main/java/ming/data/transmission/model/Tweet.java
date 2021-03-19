package ming.data.transmission.model;

import lombok.Data;

/**
 * @author gary
 */
@Data
public class Tweet {
    private long id;
    private String source;
    private String text;
    private boolean favorited;
    private User user;

    @Data
    static class User {
        private long id;
        private String name;
        private String description;
        private String url;
    }
}
