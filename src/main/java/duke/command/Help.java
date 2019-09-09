package duke.command;

public class Help {

    private static String help_message = "Here are the lists of commands available:\n\n" +
            "list - displays a list of all available tasks\n\n" +
            "todo <description> - Adds a new todo.\n\n" +
            "deadline <description> /by <dd/MM/yyyy HHmm> - Adds a new deadline.\n\n" +
            "event <description> /at <dd/MM/yyyy HHmm> - Adds a new event.\n\n" +
            "done <number> - marks the entry with the corresponding number done.\n\n" +
            "delete <number> - deletes the entry with the corresponding number.\n\n" +
            "help - displays this help menu.\n";

    static String help() {
        return help_message;
    }
}
