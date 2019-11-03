

public class TaskService {
    private TaskRepository tasks;

    public TaskService() {
        this.tasks = new TaskArray();
    }

    public void addTask(Task task) {
        if(task == null) return;
        for(int index = 0; index < tasks.size(); index++){
            if(tasks.getTask(index) == null){
                tasks.setTask(index,task);
                return;
            }
        }
        System.out.println("ERROR: Task list is full, task '" + task.getName() + "' isn't inserted");
    }

    public void deleteTask(int taskId) {
        for(int index = 0; index < tasks.size(); index++){
            if(tasks.getTask(index) != null && tasks.getTask(index).getId() == taskId){
                tasks.setTask(index, null);
                return;
            }
        }
        System.out.println("ERROR: Task not found (id = " + taskId + ")");
    }

    public void deleteTask(String taskName) {
        for(int index = 0; index < tasks.size(); index++){
            if(tasks.getTask(index) != null && tasks.getTask(index).getName().equals(taskName)){
                tasks.setTask(index, null);
                return;
            }
        }
        System.out.println("ERROR: Task not found (name: '" + taskName + "')");
    }

    @Override
    public String toString() {return tasks.toString(); }

    public void info(){
        System.out.println(this);
    }
}
