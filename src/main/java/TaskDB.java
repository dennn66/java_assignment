import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class TaskDB  implements TaskRepository {
    private static final String connectString = "jdbc:sqlite:tasks.db";
    private static Connection connection;
    private static Statement stmt;

    public TaskDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(connectString);
        stmt = connection.createStatement();
        createTableEx();
    }


    @Override
    public void addTask(Task task) {
        if (task == null) throw new NullPointerException();
        try {
            getTask(task.getId());
            throw new TaskExistException("Task already exist");
        } catch (TaskNotFoundException e) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO " +
                    "tasks(name, creator, assignee, description, status, id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")){
                ps.setString(1, task.getName());
                ps.setString(2, task.getCreator());
                ps.setString(3, task.getAssignee());
                ps.setString(4, task.getDescription());
                ps.setString(5, task.getStatus().name());
                ps.setLong(6, task.getId());
                int rs = ps.executeUpdate();
                System.out.println("Inserted: " + rs);
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateTask(Task task) {
        //UPDATE students SET score = 100 WHERE name = 'Bob4';"
        try(PreparedStatement ps = connection.prepareStatement("UPDATE tasks SET name=?, " +
                "creator=?, assignee=?,description=?, status=? WHERE id=?")) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getCreator());
            ps.setString(3, task.getAssignee());
            ps.setString(4, task.getDescription());
            ps.setString(5, task.getStatus().name());
            ps.setLong(6, task.getId());
            int rs = ps.executeUpdate();
            System.out.println("Updated: " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskNotFoundException("Task not found");
        }
    }

    @Override
    public void deleteTask(Task task) {
        try(PreparedStatement ps = connection.prepareStatement("DELETE FROM tasks WHERE id=?")) {

            ps.setLong(1, task.getId());
            int rs = ps.executeUpdate();
            System.out.println("Deleted: " + rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try(ResultSet rs = stmt.executeQuery("SELECT id, name, creator, assignee,description, status FROM tasks")) {
            while (rs.next()){
                tasks.add(new Task(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("creator"),
                    rs.getString("assignee"),
                    rs.getString("description"),
                    Task.Status.valueOf(rs.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    private Task getTask(Long id) {
        try(PreparedStatement ps = connection.prepareStatement("SELECT id, name, creator, " +
                "assignee, description, status " +
                "FROM tasks " +
                "WHERE id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.isClosed()) throw new TaskNotFoundException("Task not found");
            else return new Task(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("creator"),
                    rs.getString("assignee"),
                    rs.getString("description"),
                    Task.Status.valueOf(rs.getString("status")));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TaskNotFoundException("Task not found");
        }

    }

    private static void createTableEx() throws SQLException {
        //name, creator, assignee, description, status
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tasks (\n" +
                "        pk    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "        id  TEXT,\n" +
                "        name  TEXT,\n" +
                "        creator  TEXT,\n" +
                "        assignee  TEXT,\n" +
                "        description  TEXT,\n" +
                "        status  TEXT\n" +
                "    );");
    }
    @Override
    public void clear() {
        try {
            stmt.executeUpdate("DELETE FROM tasks;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // I think I don't need to disconnect
    public static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
