

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() {
        taskRepository = new TaskArray();
    }

    public void addTask(Task task) throws TaskRepositoryIsFullException { taskRepository.addTask(task); }
    public Task findTask(Long taskId) throws TaskNotFoundException { return taskRepository.findTask(taskId);}
    public Task findTask(String taskName) throws TaskNotFoundException {return taskRepository.findTask(taskName);}
    public void deleteTask(Long taskId) throws TaskNotFoundException { taskRepository.deleteTask(taskId);}
    public void deleteTask(String taskName) throws TaskNotFoundException {taskRepository.deleteTask(taskName);}

    @Override
    public String toString() {
        Task[] tasks = taskRepository.getTasks();
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) taskList.append(task).append("\r\n");
        }
        return taskList.toString();
    }

    public void info(){
        System.out.println(this);
    }
}
