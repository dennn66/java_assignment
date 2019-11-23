import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        TaskService tracker = null;
        try {
            tracker = new TaskService();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tracker.test();
    }
}
