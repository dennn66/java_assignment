public interface TaskRepository {
    Task findTask(Long taskId);
    Task findTask(String taskName);
    void addTask(Task task);
    void deleteTask(Long taskId);
    void deleteTask(String taskName);
    String toString();
}
