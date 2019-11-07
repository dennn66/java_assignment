import java.util.ArrayList;

public interface TaskRepository {
    Task findTask(Long taskId);
    Task findTask(String taskName);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Long taskId);
    void deleteTask(String taskName);
    ArrayList<Task> getTasks();
}
