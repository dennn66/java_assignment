import java.util.ArrayList;

public interface TaskRepository {
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
    ArrayList<Task> getTasks();
}
