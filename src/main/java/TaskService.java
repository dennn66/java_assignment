

public class TaskService implements TaskRepository {
    private TaskRepository tasks;

    public TaskService() {
        this.tasks = new TaskArray();
    }

    public void addTask(Task task) { tasks.addTask(task); }
    public Task findTask(Long taskId) { return tasks.findTask(taskId);}
    public Task findTask(String taskName) {return tasks.findTask(taskName);}
    public void deleteTask(Long taskId) { tasks.deleteTask(taskId);}
    public void deleteTask(String taskName) {tasks.deleteTask(taskName);}

    @Override
    public String toString() {return tasks.toString(); }

    public void info(){
        System.out.println(this);
    }
}
