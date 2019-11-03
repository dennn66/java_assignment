public interface TaskRepository {
    Task getTask(int index);
    void setTask(int index, Task task);
    long size();
    String toString();
}
