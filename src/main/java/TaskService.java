

public class TaskService implements TaskRepository {
    private TaskRepository tasks;

    public TaskService() {
        this.tasks = new TaskArray();
    }

    @Override
    public void addTask(Task task) { tasks.addTask(task); }

    @Override
    public Task findTask(Long taskId) { return tasks.findTask(taskId);}

    @Override
    public Task findTask(String taskName) {return tasks.findTask(taskName);}

    @Override
    public void deleteTask(Long taskId) { tasks.deleteTask(taskId);}

    @Override
    public void deleteTask(String taskName) {tasks.deleteTask(taskName);}

    @Override
    public String toString() {return tasks.toString(); }

    public void info(){
        System.out.println(this);
    }
}
