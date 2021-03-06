package duke.ui;

import java.awt.Desktop;
import java.net.URI;
import java.util.Arrays;

import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.DukeException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * duke.main.Duke class. Creates a new duke.main.Ui, duke.command.TaskList and duke.command.Storage object,
 * then runs the duke.main method of the program.
 */
public class Duke {

    /** Stores the duke.main.Ui object used to display messages to the user. */
    private Ui ui;
    /** Stores the duke.command.Storage qobject used to read/write from file. */
    private Storage storage;
    /** Stores the duke.command.TaskList object used to add/delete tasks. */
    private TaskList taskList;
    /** Easter egg counter. */
    private int larry = 5;

    // Solution below adapted from https://github.com/nexolute/duke/blob/master/src/main/java/duke/Duke.java
    /**
     * Initializes the duke.main.Duke object by setting the duke.main.Ui,
     * duke.command.Storage and duke.command.TaskList objects.
     * @param mainWindow the duke.main.MainWindow of the application.
     */
    public void initialize(MainWindow mainWindow) {
        ui = new Ui(mainWindow);
        storage = new Storage("data/larry.txt");
        taskList = new TaskList(storage);
        ui.printIntro();
    }

    /**
     * Requests a response from duke.main.Duke.
     * @param input The input string from the user.
     */
    void getResponse(String input) {
        int index;
        try {
            String[] userInput = Parser.parseUserInput(input);
            String command = userInput[0];
            String[] params = Arrays.copyOfRange(userInput, 1, userInput.length);

            switch (command) {
            case "bye":
                ui.printGoodbye();

                //@@author pohlinwei-reused
                // Taken from https://github.com/pohlinwei/duke/blob/master/src/main/java/duke/util/ui/MainWindow.java.
                // Line 64 - 67.
                // Allows the exit message to be printed before the program closes.
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();

                //@@author

                break;

            case "list":
                ui.printToUser(taskList.list());
                break;

            case "done":
                index = Parser.checkForValidIndex(params);
                ui.printToUser(taskList.markAsDone(index));
                break;

            case "delete":
                index = Parser.checkForValidIndex(params);
                ui.printToUser(taskList.delete(index));
                break;

            case "todo":
                ui.printToUser(taskList.createToDo(params));
                break;

            case "deadline":
                ui.printToUser(taskList.createDeadline(params));
                break;

            case "event":
                ui.printToUser(taskList.createEvent(params));
                break;

            case "note":
                ui.printToUser(taskList.createNote(params));
                break;

            case "find":
                ui.printToUser(taskList.findEvent(params));
                break;

            case "help":
                ui.printToUser(taskList.help());
                break;

            case "larry":
                if (larry == 1) {
                    //@@author TorstenH.-reused
                    // Taken from https://www.codeproject.com/Questions/398241/how-to-open-url-in-java
                    // Opens a URL in the user's default browser.
                    try {
                        Desktop desktop = java.awt.Desktop.getDesktop();
                        URI url = new URI("https://www.youtube.com/watch?v=_uv8Ej4CEoQ");
                        desktop.browse(url);
                    } catch (Exception e) {
                        throw new DukeException("Oops, failed to launch Easter Egg.");
                    }
                    //@@author
                } else {
                    larry--;
                }
                break;

            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException | InterruptedException e) {
            ui.printErrToUser(e);
        }
    }


}
