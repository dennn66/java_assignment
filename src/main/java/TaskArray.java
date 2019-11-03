public class TaskArray implements TaskRepository {
    private Task[] tasks;

    public TaskArray() {
        this.tasks = new Task[10];
    }

    @Override
    public Task getTask(int index) {
        return tasks[index];
    }

    @Override
    public void setTask(int index, Task task) {
        this.tasks[index] = task;
    }

    @Override
    public long size() {
        return tasks.length;
    }

    @Override
    public String toString() {
        String taskList = "";
        for (Task task : this.tasks) {
            if (task != null) taskList += task.toString() + "\r\n";
        }
        return taskList;
    }
}
