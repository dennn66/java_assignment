

public class TaskTracker {
    private Task[] tasks;

    public TaskTracker() {
        this.tasks = new Task[10];
    }

//    public Task[] getTasks() {
//        return tasks;
//    }

    public boolean addTask(Task task) {
        for(int index = 0; index < tasks.length; index++){
            if(tasks[index] == null){
                tasks[index] = task;
                return true;
            }
        }
        System.out.println("ERROR: Task list is full, task '" + task.getName() + "' not inserted");
        return false;
    }

    public void deleteTask(int task_id) {
        for(int index = 0; index < tasks.length; index++){
            if(tasks[index] != null && tasks[index].getId() == task_id){
                tasks[index] = null;
            }
        }
    }

    public void deleteTask(String task_name) {
        for(int index = 0; index < tasks.length; index++){
            if(tasks[index] != null && tasks[index].getName().equals(task_name)){
                tasks[index] = null;
            }
        }
    }

    @Override
    public String toString() {
        String taskList = "";
        for(Task task: this.tasks){
            if(task != null) taskList += task.toString() + "\r\n";
        }
        return taskList;
    }
}
