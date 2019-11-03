public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");
        TaskService tracker = new TaskService();
        for(Long taskId = 1000L; taskId< 1011L; taskId++){
            Task task = new Task(taskId, "Important task #" + taskId, "Bill",  "very important task");
            tracker.addTask(task);
            task.info();
        }
        tracker.addTask(null);
        tracker.findTask(1004L).setAssignee("John");
        tracker.findTask("Important task #1006").setAssignee("John");
        tracker.findTask("Important task #1002").closeTask();
        tracker.findTask("Important task #1002").setAssignee("John");
        tracker.deleteTask(1003L);
        tracker.deleteTask(1015L);
        tracker.deleteTask("Important task #1007");
        tracker.deleteTask("Important task #1015");
        tracker.info();
    }
}
