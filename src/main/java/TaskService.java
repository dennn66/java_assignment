import java.util.ArrayList;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() {
        taskRepository = new TaskList();
    }

    public void addTask(Task task)          { taskRepository.addTask(task); }
    public void updateTask(Task task)       { taskRepository.updateTask(task); }
    public Task findTask(Long taskId)       { return taskRepository.findTask(taskId);}
    public Task findTask(String taskName)   { return taskRepository.findTask(taskName);}
    public void deleteTask(Long taskId)     { taskRepository.deleteTask(taskId);}
    public void deleteTask(String taskName) { taskRepository.deleteTask(taskName);}

    @Override
    public String toString() {
        ArrayList <Task> tasks = taskRepository.getTasks();
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) taskList.append(task).append("\r\n");
        }
        return taskList.toString();
    }

    public void info(){
        System.out.println(this);
    }

    public void test(){

        for(Long taskId = 1000L; taskId< 1011L; taskId++){
            Task task = new Task(taskId, "Important task #" + taskId, "Bill",  "very important task");
            try {
                addTask(task);
                task.info();
            } catch (TaskExistException e){
                System.out.println(e.getMessage());
            }
        }
        addTask(null);
        findTask(1004L).setAssignee("John");
        findTask("Important task #1006").setAssignee("John");
        findTask("Important task #1002").closeTask();
        findTask("Important task #1002").setAssignee("John");
        deleteTask(1003L);
        try {
            deleteTask(1015L);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        deleteTask("Important task #1007");
        try {
            deleteTask("Important task #1015");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        info();

    }
}
