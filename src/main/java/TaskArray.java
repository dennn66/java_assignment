public class TaskArray implements TaskRepository {
    private Task[] tasks;
    private final int size = 10;

    public TaskArray() {
        tasks = new Task[size];
    }

    private Task getTask(Integer index)  throws TaskNotFoundException {
        if(index != null && index >= 0 && index < size) return tasks[index];
        else throw new TaskNotFoundException("Task not found");
    }

    private void setTask(Integer index, Task task) throws TaskNotFoundException {
        if(index != null && index >= 0 && index < size) tasks[index] = task;
        else throw new TaskNotFoundException("Task not found");
    }


    private int findTaskIndex(Long taskId) {
        for(int index = 0; index < size; index++){
            if(getTask(index) != null && getTask(index).getId().equals(taskId)){
                return index;
            }
        }
        return -1;
    }

    private int findTaskIndex(String taskName) {
        for(int index = 0; index < size; index++){
            if(getTask(index) != null && getTask(index).getName().equals(taskName)) return index;
        }
        return -1;
    }
    private int findTaskIndex(Task task) {
        for(int index = 0; index < size; index++){
            if(task == null){
                if(getTask(index) == null)  return index;
            } else if(getTask(index).equals(task)) return index;
        }
        return -1;
    }

    private void deleteTaskByIndex(int index) {  setTask(index, null); }


    @Override
    public void addTask(Task task) throws TaskRepositoryIsFullException {
        if(task == null) return;
        int index = findTaskIndex((Task) null);

        if(index < 0)  throw new TaskRepositoryIsFullException("Task repository is full, task '" +
                    task.getName() + "' isn't inserted");
        else setTask(index,task);
    }

    @Override
    public Task findTask(Long taskId) throws TaskNotFoundException { return getTask(findTaskIndex(taskId)); }

    @Override
    public Task findTask(String taskName)  throws TaskNotFoundException { return getTask(findTaskIndex(taskName)); }

    @Override
    public void deleteTask(Long taskId)  throws TaskNotFoundException { deleteTaskByIndex(findTaskIndex(taskId)); }

    @Override
    public void deleteTask(String taskName)  throws TaskNotFoundException { deleteTaskByIndex(findTaskIndex(taskName)); }

    @Override
    public Task[] getTasks() { return tasks; }
}
