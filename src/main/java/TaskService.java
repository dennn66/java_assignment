import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() {
        taskRepository = new TaskList();
    }

    public void addTask(Task task)          { taskRepository.addTask(task); }
    public void updateTask(Task task)       { taskRepository.updateTask(task); }


    public List<Task> findTasksByStatus(Task.Status status)       {

        return taskRepository.getTasks().stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());

    }

    public List<Task> sortTasksByStatus()       {

        return taskRepository.getTasks().stream().sorted().collect(Collectors.toList());
    }

    public long countTasksByStatus(Task.Status status)       {
        return findTasksByStatus(status).size();
    }

    public Task findTask(Long taskId)       {
        return taskRepository.getTasks().stream().
                filter(t -> t.getId().
                equals(taskId)).
                findFirst().
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
    public Task findTask(String taskName)   {
        return taskRepository.getTasks().stream().
                filter(t -> t.getName().
                equals(taskName)).
                findFirst().
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
    public void deleteTask(Long taskId)     { taskRepository.deleteTask(findTask(taskId));}
    public void deleteTask(String taskName) { taskRepository.deleteTask(findTask(taskName));}

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
        //addTask(null);
        System.out.println("Find existing task:");
        findTask(1004L).info();
        System.out.println("Find not existing task:");
        try {
            findTask(1040L).info();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

        findTask(1004L).setAssignee("John");
        findTask("Important task #1006").setAssignee("John");
        findTask("Important task #1008").closeTask();
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
        //System.out.println(findTasksByStatus(Task.Status.ASSIGNED));
        System.out.println("findTasksByStatus");
        try {
            System.out.println(findTasksByStatus(Task.Status.ASSIGNED));
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("sortTasksByStatus");
        try {
            System.out.println(sortTasksByStatus());
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("countTasksByStatus");
        try {
            System.out.println(countTasksByStatus(Task.Status.ASSIGNED));
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
