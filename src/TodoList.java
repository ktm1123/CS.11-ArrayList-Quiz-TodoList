import java.util.ArrayList;

public class TodoList {

    private String owner;
    private ArrayList<Task> tasks;

    public TodoList(String owner) {
        this.owner = owner;
        this.tasks = new ArrayList<Task>();
    }

    public boolean addTask(String name, int urgency) {
        for (Task task : tasks) {
            if (task.getName().equals(name))
                return false;
        }
        tasks.add(new Task(name, urgency));
        return true;
    }

    public boolean removeTask(String name) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().equals(name)) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateTaskUrgency(String name, int urgency) {

        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                task.setUrgency(urgency);
                return true;
            }
        }
        return false;
    }

    /**
     * The method mostUrgent will return the name of the task that is the most urgent (i.e. the task with the
     * highest urgency). If there are multiple tasks whose urgency is equal to the maximum, the first task
     * with that urgency will be returned.
     *
     * @return the name of the task with the current highest urgency (a String).
     */
    public String mostUrgent() {
        if (tasks.isEmpty()) {
            return null;
        }

        Task mostUrgentTask = tasks.get(0);
        for (int i = 1; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getUrgency() > mostUrgentTask.getUrgency()) {
                mostUrgentTask = currentTask;
            }
        }

        return mostUrgentTask.getName();
    }

    /**
     * The method averageUrgency will return the average (arithmetic mean) of the urgency across all tasks
     *
     * @return the average urgency across all tasks (a double).
     */
    public double averageUrgency() {
        if (tasks.isEmpty()) {
            return 0.0;
        }

        int totalUrgency = 0;
        for (Task task : tasks) {
            totalUrgency += task.getUrgency();
        }

        double average = (double) totalUrgency / tasks.size();
        return average;
    }

    /**
     * The method toString will return all tasks with their urgency in the form of a String.
     * The returned String will be in the following format:
     *
     * To-do List of [owner]
     * [Task 1] [tab] [urgency]
     * [Task 2] [tab] [urgency]
     * [Task 3] [tab] [urgency]
     * [Task 4] [tab] [urgency]
     * ...
     * @return the printable representation of the to-do list (a String).
     *
     * BONUS Challenge:  Sort your to-do list in descending order of urgency
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("To-do List of ").append(owner).append("\n");

        List<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks, Comparator.comparingInt(Task::getUrgency).reversed());

        for (Task task : sortedTasks) {
            sb.append(task.getName()).append("\t").append(task.getUrgency()).append("\n");
        }

        return sb.toString();
    }
}
