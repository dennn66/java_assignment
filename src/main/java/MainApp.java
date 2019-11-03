public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");
        TaskService tracker = new TaskService();
        for(Long taskId = 1000L; taskId< 1011L; taskId++){
            Task task = new Task(taskId, "Important task #" + taskId, "Bill",  "very important task");
            tracker.addTask(task);
            if( taskId % 2 == 0) task.setAssignee("John");
            if( taskId % 3 == 0) task.closeTask();
            task.info();
        }
        tracker.addTask(null);
        tracker.deleteTask(1003);
        tracker.deleteTask(1015);
        tracker.deleteTask("Important task #1007");
        tracker.deleteTask("Important task #1015");
        tracker.info();
    }
}
