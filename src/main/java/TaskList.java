import java.util.ArrayList;

public class TaskList implements TaskRepository {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    private void setTask(Integer index, Task task)  {
        if(index != null && index >= 0 && index < tasks.size()) tasks.set(index, task);
        else throw new TaskNotFoundException("Task not found");
    }

    @Override
    public void addTask(Task task) {
        if(task == null) throw new NullPointerException();
        if(tasks.indexOf(task) >= 0)  throw new TaskExistException("Task '" + task.getName() + "' already exist");
        tasks.add(task);
    }

    @Override
    public void updateTask(Task task) { setTask(tasks.indexOf(task), task);   }


    @Override
    public void deleteTask(Task task)   { tasks.remove(task); }


    @Override
    public ArrayList<Task>  getTasks() { return tasks; }
}
