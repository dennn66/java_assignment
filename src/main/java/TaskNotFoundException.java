public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String task_not_found) {
        super(task_not_found);
    }
}
