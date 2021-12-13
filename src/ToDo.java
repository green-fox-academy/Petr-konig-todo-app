import exceptions.*;
import interfaces.Handable;
import interfaces.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ToDo implements Handable, Printable {


    private final List<String> commands;


    public ToDo() {
        commands = new ArrayList<>();
        commands.add("-help  Writes out usable commands");
        commands.add("-lgout  Logs out User do it when you are done");
        commands.add("-l  Lists all the tasks");
        commands.add("-ld  Lists all done tasks");
        commands.add("-lu  Lists all undone tasks");
        commands.add("-a  Adds a new task");
        commands.add("-r  Removes an task");
        commands.add("-c  Completes an task");
    }


    @Override
    public void printUsage() {
        System.out.println("--xx Command Line Todo application xx--");
        System.out.println("=======================================");
        System.out.println();

    }

    public void authentication(User user) {
        if (user.readUser().isEmpty() || !user.isAuthorized()) {
            printUsage();
            System.out.println("--User authentication--");
            System.out.println();
            user.handleUser();
        }
    }

    public void printCommands() {
        System.out.println("Available command Line arguments:");
        for (String command : commands) {
            if (command.substring(0, 4).matches("[-]\\w{2}\\s")) {
                System.out.println("        " + command);
            } else {
                System.out.println("    " + command);
            }
        }
    }

    @Override
    public void handleArgs(String[] args) throws NoTaskProvidedException, NoIndexProvidedException, GreaterIndexException, NotANumberException, UnsupportedArgumentException, NoIndexProvidedCheckException, NotANumberCheckException, GreaterIndexCheckException {
        User user = new User();
        authentication(user);
        Task task = new Task();
        task.populateList(user);
        if (args.length == 0) {
            System.out.println(" Type -help to list all commands");
        } else if (args[0].equals("-help")) {
            printCommands();
        } else if (args[0].equals("-lgout")) {
            System.out.println("Logging out " + user.readUser());
            user.authUser("not authorized");

        } else if (args[0].equals("-l") || args[0].equals("-lu") || args[0].equals("-ld")) {
            task.printList(args[0], user);
        } else if (args[0].equals("-a")) {
            if (args.length < 2) {
                throw new NoTaskProvidedException("Unable to add: no task provided");
            }
            task = new Task(args[1]);
            task.writeTask(user);
        } else if (args[0].equals("-r")) {
            if (args.length < 2) {
                throw new NoIndexProvidedException("Unable to remove: no index provided");
            } else if (!args[1].matches("\\d+")) {
                throw new NotANumberException("Unable to remove: input is not a digit");
            } else if (Integer.parseInt(args[1]) > task.taskList.size() + 1) {
                throw new GreaterIndexException("Unable to remove: index is out of bound");
            }
            task.removeTask(Integer.parseInt(args[1]), user);
        } else if (args[0].equals("-c")) {
            if (args.length < 2) {
                throw new NoIndexProvidedCheckException("Unable to check: no index provided");
            } else if (!args[1].matches("\\d+")) {
                throw new NotANumberCheckException("Unable to check: input is not a digit");
            } else if (Integer.parseInt(args[1]) > task.taskList.size() + 1) {
                throw new GreaterIndexCheckException("Unable to check: index is out of bound");
            }
            task.checkTask(Integer.parseInt(args[1]), user);
        } else {
            throw new UnsupportedArgumentException("Unsupported argument");
        }
    }
}
