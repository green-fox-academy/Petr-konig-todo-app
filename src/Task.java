import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private String task;
    protected List<String> taskList;

    public Task() {
        taskList = new ArrayList<>();
    }

    public Task(String task) {
        this.task = task;
        taskList = new ArrayList<>();
    }


    public void populateList(User user) {
        taskList = readFile(user);
    }

    public void printList(String args, User user) {
        List<String> tempIsDone = readFile(user);
        if (taskList.isEmpty()) {
            System.out.println("There is no todos for today! :-)");
        } else {
            switch (args) {
                case "-l":
                    System.out.println("List of all tasks");
                    for (int i = 0; i < taskList.size(); i++) {
                        if (tempIsDone.get(i).split(",")[1].equals("finished")) {
                            System.out.println(i + 1 + " [X]" + " - " + taskList.get(i).split(",")[0]);
                        } else {
                            System.out.println(i + 1 + " [ ]" + " - " + taskList.get(i).split(",")[0]);
                        }
                    }
                    break;
                case "-lu":
                    System.out.println("Pending tasks: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        if (tempIsDone.get(i).split(",")[1].equals("unfinished")) {
                            System.out.println(i + 1 + " - " + taskList.get(i).split(",")[0]);
                        }
                    }
                    break;
                case "-ld":
                    System.out.println("Finished tasks: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        if (tempIsDone.get(i).split(",")[1].equals("finished")) {
                            System.out.println(i + 1 + " - " + taskList.get(i).split(",")[0]);
                        }
                    }
                    break;
            }
        }
    }

    public List<String> readFile(User user) {
        Path path = Paths.get(user.getFilePath());
        List<String> temp = new ArrayList<>();
        try {
            temp = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void writeFile(List<String> list, User user) {
        Path path = Paths.get(user.getFilePath());
        try {
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTask(User user) {
        //Reading todos
        List<String> tempTodo = readFile(user);

        //Adding New Task
        tempTodo.add(this.task + ",unfinished");

        //Writing task
        writeFile(tempTodo, user);

    }

    public void removeTask(int taskPosition, User user) {
        //Reading todos && dones
        List<String> tempTodo = readFile(user);

        //Removing task
        tempTodo.remove(taskPosition - 1);

        //Writing new todos && dones
        writeFile(tempTodo, user);

    }

    public void checkTask(int taskPosition, User user) {
        List<String> tempDone = readFile(user);

        if (tempDone.get(taskPosition - 1).split(",")[1].equals("unfinished")) {
            tempDone.set(taskPosition - 1, tempDone.get(taskPosition - 1).split(",")[0].concat(tempDone.get(taskPosition - 1).split(",")[1] = ",finished"));
            System.out.println("Task has been completed");
        } else {
            System.out.println("Task is already completed");
        }
        writeFile(tempDone, user);
    }


}
