public class Task {
    private int id;//id,
    private String name;// название,
    private String creator;// имя владельца задачи,
    private String assignee; // имя исполнителя,
    private String description; // описание,
    private String status; // статус

    public Task(int id, String name, String creator, String assignee, String description, String status) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.assignee = assignee;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id +
                " " + name +
                " status:" + status +
                " assignee:" + assignee +
                " author:" + creator +
                " description: " + description;
    }

    public void info(){
        System.out.println(this);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj == null || !(obj instanceof Task)) return false;
//        return ((Task)obj).id == this.id;
//    }
}
