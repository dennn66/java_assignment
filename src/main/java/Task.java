public class Task implements  Comparable<Task>{
    private Long id;//id,
    private String name;// название,
    private String creator;// имя владельца задачи,
    private String assignee; // имя исполнителя,
    private String description; // описание,
    private Status status; // статус

    @Override
    public int compareTo(Task task) {
        return status.priority - task.status.priority;
    }

    enum Status {
        CREATED("Открыта", 1), ASSIGNED("Назначена", 2), COMPLETED("Завершена", 3);
        String name;
        int priority;

        Status(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "'" + name + "'";
        }
    }

    public Task(Long id, String name, String creator, String description) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.description = description;
        status = Status.CREATED;
    }

    public Status getStatus() {
        return status;
    }
    public void closeTask() {
        status = Status.COMPLETED;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
        if(assignee != null) status = Status.ASSIGNED;
        else status = Status.CREATED;
    }
    public Long getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        StringBuilder TaskString = new StringBuilder();
        return TaskString.append(id).
                append(" ").append(name).
                append(" status:").append(status).
                append(" assignee:").append(assignee).
                append(" author:").append(creator).
                append(" description: ").append(description).toString();
    }

    public void info(){  System.out.println(this); }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Task)) return false;
        return ((Task)obj).id.equals(id) && ((Task)obj).name.equals(name);
    }

    @Override
    public int hashCode() { return id.intValue() + name.hashCode(); }
}
