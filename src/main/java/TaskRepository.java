import java.sql.SQLException;
import java.util.List;

public interface TaskRepository {
    void addTask(Task task) ;
    void updateTask(Task task);
    void deleteTask(Task task);
    List<Task> getTasks();
    void clear();
}
