public class Task {
    private Long id;//id,
    private String name;// название,
    private String creator;// имя владельца задачи,
    private String assignee; // имя исполнителя,
    private String description; // описание,
    private Status status; // статус
    enum Status {
        CREATED("Открыта"), ASSIGNED("Назначена"), COMPLETED("Завершена");
        String name;

        Status(String name) {
            this.name = name;
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
        this.status = Status.CREATED;
    }

    public Status getStatus() {
        return status;
    }

    public void closeTask() {
        this.status = Status.COMPLETED;
    }

    public void setAssignee(String assignee) {
        if(this.status != Status.COMPLETED) {
            this.assignee = assignee;
            this.status = (this.assignee == null) ? Status.CREATED : Status.ASSIGNED;
        } else {
            //Raise Exception
            System.out.println("ERROR: Task is completed");
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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

    public void info(){
        System.out.println(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(
                this.id == null ||
                this.name == null ||
                !(obj instanceof Task) ||
                ((Task) obj).id == null ||
                ((Task) obj).name == null
        ) return false;
        return ((Task)obj).id.equals(this.id) && ((Task)obj).name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return (int) ((this.id + name.hashCode()) % Integer.MAX_VALUE);
    }
}
