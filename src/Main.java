import exceptions.*;

public class Main {

    public static void main(String[] args) {
        ToDo app = new ToDo();

        try {
            app.handleArgs(args);
        } catch (NoTaskProvidedException | NoIndexProvidedException | GreaterIndexException | NotANumberException | NotANumberCheckException | GreaterIndexCheckException | NoIndexProvidedCheckException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
            app.printCommands();
        }
    }
}
