public class TaskArray implements TaskRepository {
    private Task[] tasks;
    private final int size = 10;

    public TaskArray() {
        this.tasks = new Task[size];
    }

    private Task getTask(Integer index) {
        if(index != null && index >= 0 && index < size) return tasks[index];
        else return null;
    }

    private void setTask(Integer index, Task task) {
        if(index != null && index >= 0 && index < size) this.tasks[index] = task;
        else System.out.println("ERROR: Index out of range");
    }


    private Integer findTaskIndex(Long taskId) {
        for(int index = 0; index < size; index++){
            if(getTask(index) != null && getTask(index).getId().equals(taskId)){
                return index;
            }
        }
        return null;
    }

    private Integer findTaskIndex(String taskName) {
        for(int index = 0; index < size; index++){
            if(getTask(index) != null && getTask(index).getName().equals(taskName)){
                return index;
            }
        }
        return null;
    }
    private Integer findTaskIndex(Task task) {
        for(int index = 0; index < size; index++){
            if(task == null){
                if(getTask(index) == null){
                    return index;
                }
            } else if(getTask(index).equals(task)){
                return index;
            }
        }
        return null;
    }

    private void deleteTaskByIndex(Integer index) {
        if(index != null){
            setTask(index, null);
        } else {
            System.out.println("ERROR: Task not found");
        }
    }


    @Override
    public void addTask(Task task) {
        if(task == null) return;
        Integer index = findTaskIndex((Task) null);
        if(index == null) {
            System.out.println("ERROR: Task list is full, task '" + task.getName() + "' isn't inserted");
        } else {
            setTask(index,task);
        }
    }

    @Override
    public Task findTask(Long taskId) { return getTask(findTaskIndex(taskId)); }

    @Override
    public Task findTask(String taskName) { return getTask(findTaskIndex(taskName)); }

    @Override
    public void deleteTask(Long taskId) { deleteTaskByIndex(findTaskIndex(taskId)); }

    @Override
    public void deleteTask(String taskName) { deleteTaskByIndex(findTaskIndex(taskName)); }

    @Override
    public String toString() {
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) taskList.append(task).append("\r\n");
        }
        return taskList.toString();
    }
}
