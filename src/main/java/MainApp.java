public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");
        TaskTracker tracker = new TaskTracker();
        for(int i = 0; i< 11; i++){
            Task task = new Task(i, "Important task #" + i, "Bill", "John",
                    "very important task", "assigned");
            tracker.addTask(task);
            task.info();
        }
        tracker.addTask(null);
        tracker.deleteTask(3);
        tracker.deleteTask(15);
        tracker.deleteTask("Important task #7");
        tracker.deleteTask("Important task #15");
        tracker.info();
    }
}
