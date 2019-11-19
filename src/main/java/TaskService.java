import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() throws SQLException, ClassNotFoundException {
        taskRepository = new TaskDB();
    }

    public void addTask(Task task)          {taskRepository.addTask(task);}
    public void updateTask(Task task)       {taskRepository.updateTask(task);}

    public void exportTasks(String filename, List <Task> tasks){
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))){
            System.out.println("exporting " + tasks.size() + " tasks to " + filename + "...");
            tasks.forEach(task -> {
                try {
                    System.out.println("exporting task " + task.getId() + "...");
                    out.writeLong(task.getId());
                    out.writeUTF(task.getName());
                    out.writeUTF(task.getCreator());
                    out.writeUTF(task.getAssignee());
                    out.writeUTF(task.getDescription());
                    out.writeUTF(task.getStatus().name());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List <Task> importTasks(String filename){
        ArrayList<Task> tasks = new ArrayList<>();
        try(DataInputStream in = new DataInputStream(new FileInputStream(filename))){
            System.out.println("importing tasks from " + filename + "...");
            while(true) {
                Long id = in.readLong();//id,
                System.out.println("importing task " + id + "...");
                String name = in.readUTF();// название,
                String creator = in.readUTF();// имя владельца задачи,
                String assignee = in.readUTF(); // имя исполнителя,
                String description = in.readUTF(); // описание,
                String status = in.readUTF(); // статус

                Task task = new Task(id, name, creator, description);
                task.setAssignee(assignee);
                task.setStatus(Task.Status.valueOf(status));
                tasks.add(task);
                System.out.println(task);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Tasks import is completed. Loaded " + tasks.size() + " tasks");
        }
        return tasks;
    }

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
                filter(t -> t.getId().equals(taskId)).
                findFirst().
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
    public Task findTask(String taskName)   {
        return taskRepository.getTasks().stream().
                filter(t -> t.getName().equals(taskName)).
                findFirst().
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
    public void deleteTask(Long taskId)     {taskRepository.deleteTask(findTask(taskId));}
    public void deleteTask(String taskName) {taskRepository.deleteTask(findTask(taskName));}

    @Override
    public String toString() {
        List <Task> tasks = taskRepository.getTasks();
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
        taskRepository.clear();

        for(Long taskId = 1000L; taskId< 1011L; taskId++){
            Task task = new Task(taskId, "Important task #" + taskId, "Bill",  "very important task");
            try {
                addTask(task);
                task.info();
            } catch (TaskExistException e){
                System.out.println(e.getMessage());
            }
        }
        try {
            System.out.println("Adding null task...");
            addTask(null);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Adding existing task...");
            addTask(new Task(1000L, "Important task #double", "Bill",  "very important task"));
        } catch (TaskExistException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Find existing task:");
        Task t = findTask(1004L);
        t.info();
        t.setStatus(Task.Status.COMPLETED);
        taskRepository.updateTask(t);
        System.out.println("Find not existing task:");
        try {
            findTask(1040L).info();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

        findTask(1004L).setAssignee("John");
        //findTask("Important task #1006").setAssignee("John");
        findTask("Important task #1008").closeTask();
        //findTask("Important task #1002").setAssignee("John");
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
        System.out.println(findTasksByStatus(Task.Status.ASSIGNED));
        System.out.println("sortTasksByStatus");
        System.out.println(sortTasksByStatus());
        System.out.println("countTasksByStatus");
        System.out.println(countTasksByStatus(Task.Status.ASSIGNED));
        exportTasks("tasks.data", taskRepository.getTasks());
        System.out.println(importTasks("tasks.data"));
    }
}
