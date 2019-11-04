import ExceptionAssignement.MyArrayDataException;
import ExceptionAssignement.MySizeArrayException;
import ExceptionAssignement.StringToIntArraySummator;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Program start");
        String[][][] matrixs = {
                {
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","1", "1"},
                        {"1", "1","1","1"}
                },
                {
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","1"},
                        {"1", "1","1", "1"},
                },
                {
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","Q","1"},
                        {"1", "1","1","1"}
                },
                {
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","1","1"},
                        {"1", "1","1","1"}
                },
        };
        for(String[][] matrix: matrixs){
            try{
                int result = StringToIntArraySummator.sum(matrix);
                System.out.println("Conversion result: " + result);
            } catch (MySizeArrayException | MyArrayDataException e) {
                e.printStackTrace();
            }
        }




        TaskService tracker = new TaskService();

        for(Long taskId = 1000L; taskId< 1011L; taskId++){
            Task task = new Task(taskId, "Important task #" + taskId, "Bill",  "very important task");
            try {
                tracker.addTask(task);
                task.info();
            } catch (TaskRepositoryIsFullException e){
                System.out.println(e.getMessage());
            }
        }
        tracker.addTask(null);
        tracker.findTask(1004L).setAssignee("John");
        tracker.findTask("Important task #1006").setAssignee("John");
        tracker.findTask("Important task #1002").closeTask();
        tracker.findTask("Important task #1002").setAssignee("John");
        tracker.deleteTask(1003L);
        try {
            tracker.deleteTask(1015L);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        tracker.deleteTask("Important task #1007");
        try {
            tracker.deleteTask("Important task #1015");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        tracker.info();
    }
}
