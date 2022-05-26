package java.Streams;

import java.util.List;

public class Blog {
    private String authorName;
    private List<String> comments;

    public Blog(String authorName, List<String> comments) {
        this.authorName = authorName;
        this.comments = comments;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
