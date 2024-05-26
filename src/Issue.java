import java.util.ArrayList;
import java.util.List;

public class Issue {
    String title;
    String description;
    List<String> comment;
    String status;
    String priority;

    public Issue(String title, String description, List<String> comment, String status, String priority) {
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.status = status;
        this.priority = priority;
    }
}
