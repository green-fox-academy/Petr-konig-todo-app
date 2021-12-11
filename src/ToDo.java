import java.util.HashMap;
import java.util.Map;


public class ToDo implements Handable, Printable {

    protected String [] args;
    private final HashMap<String, String> commands;

    public ToDo(String [] args){
        this.args = args;
        commands = new HashMap<>();
        commands.put("-l", "Lists all the tasks");
        commands.put("-a", "Adds a new task");
        commands.put("-r", "Removes an task");
        commands.put("-c", "Completes an task");
    }


    @Override
    public void printUsage() {
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println();
        System.out.println("Command Line arguments:");
        for (Map.Entry <String, String> command: commands.entrySet()) {
            System.out.println("    " + command.getKey() + "   " + command.getValue());
        }
    }

    @Override
    public void handleArgs(){
        if(this.args.length == 0){
            printUsage();
        }else if(args[0].equals("-l")){

        }else if(args[0].equals("-a")){

        }else if(args[0].equals("-r")){

        }else if(args[0].equals("-c")){
        }
    }
}
