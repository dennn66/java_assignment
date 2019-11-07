import java.util.ArrayList;

public class TaskList implements TaskRepository {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    private Task getTask(Integer index)   {
        if(index != null && index >= 0 && index < tasks.size()) return tasks.get(index);
        else throw new TaskNotFoundException("Task not found");
    }

    private void setTask(Integer index, Task task)  {
        if(index != null && index >= 0 && index < tasks.size()) tasks.set(index, task);
        else throw new TaskNotFoundException("Task not found");
    }
    private void deleteTaskByIndex(Integer index) {
        if(index != null && index >= 0 && index < tasks.size()) tasks.remove(index);
        else throw new TaskNotFoundException("Task not found");
    }


    private int findTaskIndex(Long taskId) {
        for(int index = 0; index < tasks.size(); index++){
            if(getTask(index) != null && getTask(index).getId().equals(taskId)){
                return index;
            }
        }
        return -1;
    }

    private int findTaskIndex(String taskName) {
        for(int index = 0; index < tasks.size(); index++){
            if(getTask(index) != null && getTask(index).getName().equals(taskName)) return index;
        }
        return -1;
    }

    @Override
    public void addTask(Task task) {
        if(tasks.indexOf(task) >= 0)  throw new TaskExistException("Task '" + task.getName() + "' already exist");
        tasks.add(task);
    }

    @Override
    public void updateTask(Task task) { setTask(tasks.indexOf(task), task);   }

    @Override
    public Task findTask(Long taskId) { return getTask(findTaskIndex(taskId)); }

    @Override
    public Task findTask(String taskName)   { return getTask(findTaskIndex(taskName)); }

    @Override
    public void deleteTask(Long taskId)   { deleteTaskByIndex(findTaskIndex(taskId)); }

    @Override
    public void deleteTask(String taskName)   { deleteTaskByIndex(findTaskIndex(taskName)); }

    @Override
    public ArrayList<Task>  getTasks() { return tasks; }
}
